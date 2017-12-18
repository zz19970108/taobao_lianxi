package bwie.com.taobao_lianxi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import login.bean.User;
import login.pressnter.MyPersenter;
import login.view.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView{

    MyPersenter myPersenter;
    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private UserInfo mUserInfo;
    private EditText phone;
    private ImageView iv_login;
    private EditText pwd;
    private TextView tv_register;
    private BaseUiListener mIUiListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTencent = Tencent.createInstance(APP_ID, MainActivity.this.getApplicationContext());
        myPersenter = new MyPersenter(this);

        phone = (EditText) findViewById(R.id.phone);
        pwd = (EditText) findViewById(R.id.pwd);
        tv_register = (TextView) findViewById(R.id.tv_register);
        iv_login = (ImageView) findViewById(R.id.iv_login);

        iv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User(phone.getText().toString(),pwd.getText().toString());
                Log.e("TAG",user.getName()+user.getPass());
                myPersenter.setLogin(user);
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void LoginSuccess() {
        Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MainActivity.this,RecycleviewActivity.class);
        startActivity(intent);
    }

    @Override
    public void LoginFailed() {
        Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
    }

    public void ivLogin(View v){
        mIUiListener=new BaseUiListener();
        //all表示获取所有权限
        mTencent.login(MainActivity.this,"all",mIUiListener);
    }
    public class BaseUiListener implements IUiListener{

        @Override
        public void onComplete(Object response) {
            Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            Log.e(TAG, "response:");
            JSONObject obj=(JSONObject) response;

            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                Log.e("TAG","走到了这里");
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,"登录成功"+response.toString());
                        Intent intent=new Intent(MainActivity.this,RecycleviewActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");
                    }
                });
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this, "授权取消", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}