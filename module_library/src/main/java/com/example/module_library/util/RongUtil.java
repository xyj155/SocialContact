package com.example.module_library.util;

import android.util.Log;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


public class RongUtil {
    public static void connect(String token, final InRongIMConnect inRongIMConnect) {

            RongIM.connect("UGtmWm2X7kksxT+/5MIWEZO4GmCsqCR3HOjjTQiUBNpTdc77TLV0UPERoZCC5SqCaaMPTVnlpguZq9aFpYdd+hMB5N7UQN89", new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {

                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("LoginActivity", "--onSuccess" + userid);
                    inRongIMConnect.onConnectSuccess();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.i(TAG, "onError: "+errorCode);
                    inRongIMConnect.onConnectFailed();
                }
            });

    }

    private static final String TAG = "RongUtil";
}
