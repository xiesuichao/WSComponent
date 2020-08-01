package com.wanshare.wscomponent.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Richard on 2018/7/26
 * 通用标题栏
 * 包含:返回按钮，标题，右侧文本，右侧image，可通过自定义属性在布局文件中设置，也可以在代码中设置
 * 后续再根据需求添加
 */
public class MyToolbar extends FrameLayout {
    private static final String TAG = MyToolbar.class.getName();

    ImageView backButton;

    TextView titleTextView;

    TextView rightButton;

    ImageView rightIVBtn;

    int mBackgroundColor;
    private String mTitleText;
    private String mRightBtnText;
    private int mRightBtnDrawableId;
    private int mBackBtnDrawableId;
    private boolean mBackBtnVisible = true;
    private int mTitleTextColor;
    private int mRightBtnTextColor;
    private float mTitleTextSize;
    private float mRightaBtnTextSize;
    private int mTitleTextStyle;


    public MyToolbar(@NonNull final Context context) {
        this(context, null);
    }

    public MyToolbar(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolbar(@NonNull final Context context, @Nullable final AttributeSet attrs, @AttrRes final int defStyle) {
        super(context, attrs, defStyle);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyToolbar, 0, 0);
        final int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            final int attr = a.getIndex(i);
            if (attr == R.styleable.MyToolbar_toolbarBackBtnVisible) {
                mBackBtnVisible = a.getBoolean(attr, mBackBtnVisible);

            } else if (attr == R.styleable.MyToolbar_toolbarTitle) {
                mTitleText = a.getString(attr);

            } else if (attr == R.styleable.MyToolbar_toolbarRightBtnText) {
                mRightBtnText = a.getString(attr);

            } else if (attr == R.styleable.MyToolbar_toolbarRightBtnDrawable) {
                mRightBtnDrawableId = a.getResourceId(R.styleable.MyToolbar_toolbarRightBtnDrawable, 0);

            }else if (attr == R.styleable.MyToolbar_toolbarBackBtnDrawable) {
                mBackBtnDrawableId = a.getResourceId(R.styleable.MyToolbar_toolbarBackBtnDrawable, 0);

            } else if (attr == R.styleable.MyToolbar_toolbarBackgroundColor) {
                mBackgroundColor = a.getColor(attr, 0);

            } else if (attr == R.styleable.MyToolbar_toolbarTitleColor) {
                mTitleTextColor = a.getColor(attr, 0);

            } else if (attr == R.styleable.MyToolbar_toolbarRightBtnTextColor) {
                mRightBtnTextColor = a.getColor(attr, 0);

            } else if (attr == R.styleable.MyToolbar_toolbarTitleSize) {
                mTitleTextSize = a.getDimension(attr, 0);

            } else if (attr == R.styleable.MyToolbar_toolbarRightBtnTextSize) {
                mRightaBtnTextSize = a.getDimension(attr, 0);

            } else if (attr == R.styleable.MyToolbar_toolbarTitleTextStyle) {
                mTitleTextStyle = a.getInteger(attr, 0);

            } else {
                Log.d(TAG, "Unknown attribute for " + getClass().toString() + ": " + attr);

            }
        }
        a.recycle();

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_my_toolbar, this, true);
        initView(view);
        initAttrs();
    }

    private void initView(View view) {
        backButton = view.findViewById(R.id.btn_back);
        titleTextView = view.findViewById(R.id.tv_title);
        rightButton = view.findViewById(R.id.tv_right);
        rightIVBtn = view.findViewById(R.id.btn_right);
    }

    private void initAttrs() {
        if (mBackgroundColor != 0) {
            setBackgroundColor(mBackgroundColor);
        }
        setBackButtonVisible(mBackBtnVisible);
        setTitle(mTitleText);
        setRightButtonText(mRightBtnText);
        if (mTitleTextColor != 0) {
            setTitleTextColor(mTitleTextColor);
        }
        if (mRightBtnTextColor != 0) {
            setRightButtonTextColor(mRightBtnTextColor);
        }
        if (mRightBtnDrawableId != 0) {
            rightIVBtn.setVisibility(VISIBLE);
            rightIVBtn.setImageResource(mRightBtnDrawableId);
        } else {
            rightIVBtn.setVisibility(GONE);
        }
        if (mTitleTextSize > 0) {
            titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleTextSize);
        }
        if (mTitleTextStyle > 0) {
            switch (mTitleTextStyle) {
                case Typeface.BOLD:
                    titleTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    break;
                case Typeface.ITALIC:
                    titleTextView.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                    break;
            }
        }
        if (mRightaBtnTextSize > 0) {
            rightButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRightaBtnTextSize);
        }
        if (mBackBtnDrawableId != 0) {
            backButton.setImageResource(mBackBtnDrawableId);
        }
    }


    public void setTitleDrwable(int bear, int id) {
        Drawable drawable = getResources().getDrawable(id);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        switch (bear) {
            case 0:
                titleTextView.setCompoundDrawables(drawable, null, null, null);
                break;
            case 1:
                titleTextView.setCompoundDrawables(null, drawable, null, null);
                break;
            case 2:
                titleTextView.setCompoundDrawables(null, null, drawable, null);
                break;
            case 3:
                titleTextView.setCompoundDrawables(null, null, null, drawable);
                break;
            default:
                break;
        }

    }

    public void setTitle(@StringRes final int stringRes) {
        titleTextView.setText(stringRes);
    }

    public void setTitle(final String title) {
        titleTextView.setText(title);
    }

    public void setTitleTextColor(int color) {
        titleTextView.setTextColor(color);
    }

    public void setTitleTextSize(float textSize) {
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    public void setBackButtonVisible(final boolean visible) {
        backButton.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setOnTitleClickListener(final OnClickListener onClickListener) {
        titleTextView.setOnClickListener(onClickListener);
    }

    public void setOnBackButtonClickListener(final OnClickListener onClickListener) {
        backButton.setOnClickListener(onClickListener);
    }

    public void setRightButtonText(@Nullable final String text) {
        rightButton.setText(text);
//        rightButton.setVisibility(TextUtils.isEmpty(text) ? INVISIBLE : VISIBLE);
    }

    public void setRightButtonTextSize(float textSize) {
        rightButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    public void setRightButtonTextColor(int color) {
        rightButton.setTextColor(color);
    }

    public void setRightButtonTextEnable(boolean enable) {
//        rightButton.setTextColor(getResources().getColorStateList(R.color.selector_text_color_toolbar_right_btn));
        rightButton.setEnabled(enable);
    }

    public void setRightButtonText(@StringRes final int resId) {
        setRightButtonText(getContext().getString(resId));
    }

    public void setRightButtonTextVisible(final boolean visible) {
        rightButton.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setOnRightButtonTextClickListener(@NonNull final OnClickListener onClickListener) {
        rightButton.setOnClickListener(onClickListener);
    }

    public void setRightButtonImageVisible(final boolean visible) {
        rightIVBtn.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setOnRightButtonImageClickListener(@NonNull final OnClickListener onClickListener) {
        rightIVBtn.setOnClickListener(onClickListener);
    }

    public void setRightButtonImage(int res) {
        rightIVBtn.setImageResource(res);
    }

    public void setRightButtonImage(Drawable drawable) {
        rightIVBtn.setImageDrawable(drawable);
    }

    public void setTitleTextStyle(Typeface tf) {
        titleTextView.setTypeface(tf);
    }

    public void setBackButtonImage(int res) {
        backButton.setImageResource(res);
    }

    public void setBackButtonImage(Drawable drawable) {
        backButton.setImageDrawable(drawable);
    }
}