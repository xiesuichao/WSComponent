package com.wanshare.wscomponent.webview;

import android.app.Activity;
import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.lang.reflect.Field;

/**
 * 带进度条的WebView.
 * <p>
 * 添加了WebView释放的代码,,
 * <p>
 * Activity中onBackPressed页面回退功能需要在Activity中添加
 *
 * @author wangdunwei
 * @date 2018/7/28
 */
public class ProgressWebView extends LinearLayout {

    public static final int PROGRESS_100 = 100;
    private ProgressBar progressBar;
    private WebView webView;

    /**
     * 显示加载进度条。 true:显示加载中进度条（不是说这个设置为true了就马上显示了，而是说网页正在加载中时会显示，加载完成隐藏）
     * false:全程不显示加载中进度条
     */
    private boolean isShowProgressBar = true;

    private OnTitleChangedListener onTitleChangedListener;

    public ProgressWebView(Context context) {
        this(context, null);
    }

    public ProgressWebView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressWebView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setOrientation(VERTICAL);

        progressBar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bg));
        final float scale = context.getResources().getDisplayMetrics().density;
        addView(progressBar, LayoutParams.MATCH_PARENT, (int) (3 * scale + 0.5F));

        webView = new WebView(getContext());
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
        addView(webView, lp);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);
//        自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);
        // 开启 localStorage
        webSettings.setDomStorageEnabled(true);
        String appCachePath = context.getCacheDir().getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setUserAgentString(
                "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36");

        webView.setWebChromeClient(new WebChromeClient());
        // 防止跳出浏览器
        webView.setWebViewClient(new WebViewClient() {
            // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;

                WebView.HitTestResult hitTestResult = view.getHitTestResult();
                //hitTestResult==null解决重定向问题
                if (!TextUtils.isEmpty(url) && hitTestResult == null) {
                    view.loadUrl(url);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            //重写此方法可以让webview处理https请求
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();  //接受所有证书
            }

        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (isShowProgressBar) {
                    if (newProgress == PROGRESS_100) {
                        progressBar.setVisibility(View.GONE);
                    } else {
                        progressBar.setProgress(newProgress);
                        progressBar.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (onTitleChangedListener != null) {
                    onTitleChangedListener.onTitleChanged(ProgressWebView.this, title);
                }
            }
        });
    }

    public void loadUrl(String url) {
        webView.loadUrl(url);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public WebView getWebView() {
        return webView;
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    public void releaseAllWebViewCallback() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            try {
                Field field = WebView.class.getDeclaredField("mWebViewCore");
                field = field.getType().getDeclaredField("mBrowserFrame");
                field = field.getType().getDeclaredField("sConfigCallback");
                field.setAccessible(true);
                field.set(null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                Field sConfigCallback = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                if (sConfigCallback != null) {
                    sConfigCallback.setAccessible(true);
                    sConfigCallback.set(null, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 销毁这个View的方法，主要是为了释放WebView。请注 {@link Activity#onDestroy()} 方法调用这个方法释放资源。
     */
    public void destroy() {
        releaseAllWebViewCallback();
        removeAllViews();
        webView.setVisibility(View.GONE);
        webView.stopLoading();
        webView.getSettings().setJavaScriptEnabled(false);
        webView.setTag(null);
        webView.clearHistory();
        webView.clearView();
        webView.loadUrl("about:blank");
        webView.removeAllViews();
        webView.destroy();
        webView = null;
    }

    /**
     * 显示/隐藏进度条，隐藏进度条后加载中也不会显示进度条
     *
     * @param showProgressBar true:显示加载中进度条，false：不显示进度条
     */
    public void setShowProgressBar(boolean showProgressBar) {
        isShowProgressBar = showProgressBar;
    }


    public void setOnTitleChangedListener(OnTitleChangedListener l) {
        this.onTitleChangedListener = l;
    }

    public interface OnTitleChangedListener {
        /**
         * 标题变化监听器
         *
         * @param progressWebView ProgressWebView
         * @param title           标题
         */
        void onTitleChanged(ProgressWebView progressWebView, String title);
    }
}
