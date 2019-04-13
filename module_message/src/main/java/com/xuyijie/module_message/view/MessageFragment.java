package com.xuyijie.module_message.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_library.MyApp;
import com.example.module_library.base.BaseFragment;
import com.example.module_library.config.RouterConfig;
import com.example.module_library.logic.presenter.EmptyPresenter;
import com.example.module_library.util.InRongIMConnect;
import com.example.module_library.util.RongUtil;
import com.example.module_library.util.SharePreferenceUtil;
import com.example.module_library.weight.toast.ToastUtils;
import com.xuyijie.module_message.R;

import io.rong.imkit.MainActivity;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.push.RongPushClient;


//@Route(path = RouterConfig.HOMEPAGE)
public class MessageFragment extends BaseFragment<EmptyPresenter> {
    @Override
    public void initData() {
        initRongFragment();
    }
    private void connect(String token) {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

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
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    ToastUtils.show("错误");
                }
            });

    }
    private void initRongFragment() {
        connect("UGtmWm2X7kksxT+/5MIWEZO4GmCsqCR3HOjjTQiUBNpTdc77TLV0UPERoZCC5SqCaaMPTVnlpguZq9aFpYdd+hMB5N7UQN89");
//        RongUtil.connect("UGtmWm2X7kksxT+/5MIWEZO4GmCsqCR3HOjjTQiUBNpTdc77TLV0UPERoZCC5SqCaaMPTVnlpguZq9aFpYdd+hMB5N7UQN89", new InRongIMConnect() {
//            @Override
//            public void onConnectSuccess() {
//                RongIM.getInstance().setMessageAttachedUserInfo(true);
//                ConversationListFragment listFragment = (ConversationListFragment) ConversationListFragment.instantiate(getContext(), ConversationListFragment.class.getName());
//                Uri uri = Uri.parse("rong://com.xuyijie.lifecircle").buildUpon()
//                        .appendPath("conversationlist")
//                        .appendQueryParameter(RongPushClient.ConversationType.PRIVATE.getName(), "false")
//                        .appendQueryParameter(RongPushClient.ConversationType.GROUP.getName(), "false")
//                        .appendQueryParameter(RongPushClient.ConversationType.DISCUSSION.getName(), "false")
//                        .appendQueryParameter(RongPushClient.ConversationType.PUBLIC_SERVICE.getName(), "false")
//                        .appendQueryParameter(RongPushClient.ConversationType.SYSTEM.getName(), "false")
//                        .build();
//                listFragment.setUri(uri);
//                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//                //将融云的Fragment界面加入到我们的页面。
//                transaction.add(R.id.conversationlist, listFragment);
//                transaction.commitAllowingStateLoss();
//            }
//
//            @Override
//            public void onConnectFailed() {
//
//            }
//        });
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public int initLayout() {
        return R.layout.fragment_message;
    }

    @Override
    public EmptyPresenter initPresenter() {
        return null;
    }
}
