package me.drakeet.mddemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import me.drakeet.materialdialog.MaterialDialog;


public class MyActivity extends ActionBarActivity {

    MaterialDialog mMaterialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    public void init(View v) {
        mMaterialDialog = new MaterialDialog(this);

        Toast.makeText(getApplicationContext(), "Initializes successfully.", Toast.LENGTH_SHORT).show();
    }

    public void show(View v) {
        if (mMaterialDialog != null) {
            mMaterialDialog.setTitle("MaterialDialog");
            mMaterialDialog.setMessage(
                    "Hi! This is a MaterialDialog. It's very easy to use, you just new and show() it " +
                            "then the beautiful AlertDialog will show automatedly. It is artistic, conforms to Google Material Design." +
                            " I hope that you will like it, and enjoy it. ^ ^"
            );
            //mMaterialDialog.setBackgroundResource(R.drawable.background);
            mMaterialDialog.setPositiveButton(
                    "OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                            Toast.makeText(MyActivity.this, "Ok", Toast.LENGTH_LONG).show();

                        }
                    }
            );

            mMaterialDialog.setNegativeButton(
                    "CANCLE", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                            Toast.makeText(MyActivity.this, "Cancle", Toast.LENGTH_LONG).show();
                        }
                    }
            );
            mMaterialDialog.setCanceledOnTouchOutside(false);
            // You can change the message anytime.
            // mMaterialDialog.setTitle("提示");
            mMaterialDialog.setOnDismissListener(
                    new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            Toast.makeText(MyActivity.this, "onDismiss", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            mMaterialDialog.show();
            // You can change the message anytime.
            // mMaterialDialog.setMessage("嗨！这是一个 MaterialDialog. 它非常方便使用，你只需将它实例化，这个美观的对话框便会自动地显示出来。它简洁小巧，完全遵照 Google 2014 年发布的 Material Design 风格，希望你能喜欢它！^ ^");
        } else {
            Toast.makeText(getApplicationContext(), "You should init firstly!", Toast.LENGTH_SHORT).show();
        }
    }

    static int i = 0;

    public void setView(View v) {
        switch (v.getId()) {
            case R.id.button_set_view: {
                mMaterialDialog = new MaterialDialog(this);
                if (mMaterialDialog != null) {
                    View view = LayoutInflater.from(this).inflate(R.layout.progressbar_item, null);

                    mMaterialDialog.setView(view);
                    mMaterialDialog.show();
                } else {
                    Toast.makeText(getApplicationContext(), "You should init firstly!", Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.button_set_background: {
                mMaterialDialog = new MaterialDialog(this);
                if (mMaterialDialog != null) {
                    if (i % 2 != 0) {
                        mMaterialDialog.setBackgroundResource(R.drawable.background);
                    } else {
                        Resources res = getResources();
                        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.background2);
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bmp);
                        mMaterialDialog.setBackground(bitmapDrawable);
                    }
                    mMaterialDialog.setCanceledOnTouchOutside(true);
                    mMaterialDialog.show();
                    i++;
                    Toast.makeText(getApplicationContext(), "Try to click again~", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You should init firstly!", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.button_set_contentView: {
                /*
                if (mMaterialDialog != null) {
                    ListView listView = new ListView(this);
                    List<String> list = new ArrayList<String>();
                    for (int j = 0; j < 4; j++) {
                        list.add("item " + j);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_list_item_1,
                            list
                    );
                    listView.setAdapter(arrayAdapter);
                    mMaterialDialog.setContentView(listView);
                    mMaterialDialog.show();
                }
                */
                final MaterialDialog alert = new MaterialDialog(this);
                alert.setTitle("MaterialDialog");
                alert.setPositiveButton(
                        "OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alert.dismiss();
                            }
                        }
                );
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1
                );
                arrayAdapter.add("This is item 0");
                arrayAdapter.add("This is item 1");
                ListView listView = new ListView(this);
                float scale = getResources().getDisplayMetrics().density;
                int dpAsPixels = (int) (8 * scale + 0.5f);
                listView.setPadding(0, dpAsPixels, 0, dpAsPixels);
                listView.setDividerHeight(0);
                listView.setAdapter(arrayAdapter);

                alert.setContentView(listView);
                alert.show();

            }
        }
    }

    public void buttonPress(View view) {
        // show imm
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
        );
    }
}
