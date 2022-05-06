package com.wmding.servicelib;


import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author wmding
 * @date 3/10/22 9:28 PM
 * @describe 下载任务
 * <p>
 * 继承 AsyncTask 有3个泛型参数
 * 第一个参数：表示在执行AsyncTask的时候需要传入一个字符串类型给后台任务
 * 第二个参数：表示使用整型来作为进度显示单位
 * 第三个参数：使用整型数据来反馈执行结果
 */
public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    private static final int TYPE_SUCCESS = 0;
    private static final int TYPE_FAILED = 1;
    private static final int TYPE_PAUSED = 2;
    private static final int TYPE_CANCELED = 3;

    private DownloadListener downloadListener;


    private boolean isCanceled = false;
    private boolean isPaused = false;

    private int lastProgress = 0;

    public DownloadTask(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        long downloadLength = 0;
        String downloadUrl = strings[0];

        String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));

        String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

        File file = new File(directory + fileName);

        if (file.exists()) {
            downloadLength = file.length();
        }

        Response response;
        InputStream inputStream = null;
        RandomAccessFile savedFile = null;

        try {
            long contentLength = getContentLength(downloadUrl);

            if (contentLength == 0) {
                return TYPE_FAILED;
            } else if (contentLength == downloadLength) {
                return TYPE_SUCCESS;
            }

            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes=" + downloadLength + "-")
                    .url(downloadUrl)
                    .build();

            response = okHttpClient.newCall(request).execute();
            if (null != response) {
                inputStream = response.body().byteStream();

                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadLength);

                byte[] b = new byte[1024];
                int total = 0;
                int len;

                while ((len = inputStream.read(b)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        savedFile.write(b, 0, len);

                        int progress = (int) (((total + downloadLength) * 100) / contentLength);
                        publishProgress(progress);
                    }
                }

                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (savedFile != null) {
                    savedFile.close();
                }

                if (isCanceled && file != null) {
                    file.delete();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            downloadListener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer) {
            case TYPE_SUCCESS:
                downloadListener.onSuccess();
                break;

            case TYPE_PAUSED:
                downloadListener.onPaused();
                break;

            case TYPE_CANCELED:
                downloadListener.onCanceled();
                break;

            case TYPE_FAILED:
                downloadListener.onFailed();
                break;

            default:
                break;
        }
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void cancelDownload() {
        isCanceled = true;
    }


    /**
     * 获取文件的总长度
     * @param downloadUrl
     * @return
     * @throws IOException
     */
    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (null != response && response.isSuccessful()){
            long contentLength = response.body().contentLength();
            response.close();
            return contentLength;
        }
        return 0;
    }
}
