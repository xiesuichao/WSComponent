package com.wanshare.wscomponent.http;

import android.util.Log;
import android.util.LruCache;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * Created by huqj on  2018/7/5.
 */
public class ApiClient {
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private static final int DEFAULT_TIMEOUT = 30;
    private static ApiClient mInstance;
    private static Interceptor mInterceptor;
    private static String mBaseUrl;
    private LruCache<Class, Object> mCache = new LruCache<>(10);

    private static X509TrustManager trustAllCert = new X509TrustManager() {
        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }
    };

    //获取单例
    public static ApiClient getInstance() {
        if (mInstance == null) {
            mInstance = new ApiClient();
        }
        return mInstance;
    }

    public static void init(String baseUrl, Interceptor interceptor) {
        mBaseUrl = baseUrl;
        mInterceptor = interceptor;
    }

    public void initHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.sslSocketFactory(new MySSL(trustAllCert), trustAllCert);
        httpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        httpClientBuilder.addInterceptor(mInterceptor);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("result-http",  message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(loggingInterceptor);
        mOkHttpClient = httpClientBuilder.build();
    }

    public OkHttpClient getHttpClient() {
        if (mOkHttpClient == null) {
           initHttpClient();
        }
        return mOkHttpClient;
    }

    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .client(getHttpClient())
                    .addConverterFactory(ResponseConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(mBaseUrl)
                    .build();
        }
        return mRetrofit;
    }

    public <T> T create(final Class<T> server) {
        T t = null;
        try {
            t = (T) mCache.get(server);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (t == null) {
            Object object = getRetrofit().create(server);
            mCache.put(server, object);
            t = (T) object;
        }
        return t;
    }
}
