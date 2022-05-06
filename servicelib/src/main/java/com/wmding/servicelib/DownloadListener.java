package com.wmding.servicelib;

/**
 * @author wmding
 * @date 3/10/22 9:26 PM
 * @describe
 */
public interface DownloadListener {

    /**
     * 当前进度
     * @param progress
     */
    void onProgress(int progress);

    /**
     * 成功
     */
    void onSuccess();

    /**
     * 失败
     */
    void onFailed();

    /**
     * 暂停
     */
    void onPaused();

    /**
     * 取消
     */
    void onCanceled();

}
