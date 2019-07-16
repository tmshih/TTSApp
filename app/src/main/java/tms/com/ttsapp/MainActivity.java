package tms.com.ttsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    private View mButtonTTS;
    private TextToSpeech mTTS;
    private String mSpeechMessage = "測試看看一二三四五六七八九十! Hi! Hi! Hi!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initTTS(this);
    }

    private void initViews() {
        mButtonTTS = findViewById(R.id.buttonTTS);

        mButtonTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTTS == null) {
                    return;
                }

                Log.i(TAG, "onClick(): speak " + mSpeechMessage);
                mTTS.speak(mSpeechMessage, TextToSpeech.QUEUE_ADD, null);
            }
        });
    }

    private void initTTS(Context context) {
        if (mTTS != null) {
            return;
        }

        mTTS = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS && mTTS.isLanguageAvailable(Locale.CHINESE) == TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                    Log.i(TAG, "onInit(): " + Locale.CHINESE);
                    mTTS.setLanguage(Locale.CHINESE);
                }
            }
        });
    }
}
