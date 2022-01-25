package com.wmding.commonlib.utils;


import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author wmding
 * @date 2020/6/18
 * @describe
 */
public class HttpUtil {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static String post(String url, String msg) {
        String rspStr = null;

        RequestBody body = RequestBody.create(msg, JSON);
        OkHttpClient client = getOkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                rspStr = response.body().string();
            }
        } catch (Exception e) {
            MyLog.error("post error: " + e.getMessage());
        } finally {
            return rspStr;
        }
    }

    @NotNull
    public static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                // 为OkHttpClient设置sslSocketFactory
                .sslSocketFactory(createSSLSocketFactory(), new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                })
                //信任所有服务器地址
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .connectTimeout(20, TimeUnit.SECONDS)//设置连接超时时间
                .readTimeout(30, TimeUnit.SECONDS)//设置读取超时时间
                .build();
    }

    private static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }


    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    public static String synRequest(final String url, final String msg) {
        ExecutorService threadPool = ThreadUtil.getThreadPool();
        Future<String> submit = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                MyLog.info("url: " + url);
                MyLog.info("request: " + msg);
                String response = HttpUtil.post(url, msg);
                MyLog.info("response: " + response);
                return response;
            }
        });

        try {
            return submit.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
