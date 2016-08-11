package com.lifeistech.android.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    // 問題を管理するリスト
    private ArrayList<Question> question_list = new ArrayList<>();
    // 描画更新用Handler
    private Handler handler;
    // 1問あたりの制限時間(sec)
    private int time = 15;
    // 問題数
    private int question_num;
    // 現在の問題
    private Question current_question = null;
    // 残り時間を表示するプログレスバー
    private ProgressBar progress;
    // 残りの制限時間(sec*10)
    private int rest_time;
    // 現在の問題番号
    private int current = 0;
    // 正解を選んだ数
    private int correct_num;

    //CustomProgressBar
    View blank;
    View gifProgressBar;

    // TODO [01] ここから
    private TextView question;
    private TextView status;
    private ImageView image;
    private Button button1;
    private Button button2;
    private Button button3;
    // TODO [01] ここまで

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createCustomProgressBar();
        handler = new Handler();

        // TODO [02]ここから
        question = (TextView) findViewById(R.id.question_text);
        status = (TextView) findViewById(R.id.status);
        image = (ImageView) findViewById(R.id.question_image);
        progress = (ProgressBar)findViewById(R.id.progressBar);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        progress.setMax(time * 100);

        makeQuestions();
        startQuestion();
        // TODO [02] ここまで
    }

    // 問題を作成する
    private void makeQuestions() {
        // TODO [03] ここから
        Question q1 = new Question(R.drawable.japan,"日本の首都は？（腕試し！）","東京","京都","大阪");
        Question q2 = new Question(R.drawable.touhou2,"東方ploject第１２回（最新）の人妖部門での第一位は誰でしょう？","博麗　霊夢","霧雨　魔理沙","フランドール・スカーレット");
        Question q3 = new Question(R.drawable.touhou3,"では、音楽部門での一位の曲は、次のうちどれでしょう？","U.N オーエンは彼女なのか？","亡き王女のためのセプテット","優雅に咲かせ、墨染の桜 〜Border of life");
        Question q4 = new Question(R.drawable.touhou4,"東方紅魔郷３面ボスの（音楽）テーマは？","明治１７年の上海アリス","明治１６年の上海アリス","明治１８年の上海アリス");
        Question q5 = new Question(R.drawable.touhou5,"おまけ　( ﾟ∀ﾟ)o彡ﾟ←誰に向かってやってる？","え〜りん","衣玖サン","レミリア・スカーレット");
        Question q6 = new Question(R.drawable.touhou6,"『あなたが、コンティニュー出来ないのさ！』　この台詞のキャラは？","フランドール・スカーレット","レミリア・スカーレット","チルノ");
        Question q7 = new Question(R.drawable.touhou7,"『あなたは今まで食べてきたパンの枚数を覚えてるの？』　この台詞のキャラは？","レミリア・スカーレット","博麗　霊夢","西行寺　幽々子");
        Question q8 = new Question(R.drawable.touhou8,"『一つや二つ・・・ 結界は、そんなに少ないと思って？』　この台詞のキャラは？","八雲　紫","八雲　藍","西行寺　幽々子");
        Question q9 = new Question(R.drawable.touhou9,"永夜抄4面ボス霧雨魔理沙のﾃｰﾏは？","恋色マスタースパーク","魔女達の舞踏会","メイガスナイト");
        Question q10 = new Question(R.drawable.touhou10,"「広有射怪鳥事」をひらがなで表すと？","ひろありけちょうをいること","こうゆうしゃかいちょうじ","きれぬものなどあんまりない！");
        Question q11 = new Question(R.drawable.touhou11,"「幼心地の有頂天」←なんと読む？　（緋想天ラストスペルテーマ）","おさなごこちのうちょうてん","おさなごこちのゆうちょうてん","ようしんちのうちょうてん");
        Question q12 = new Question(R.drawable.touhou12,"最新のスペカ人気投票の第一位はどれでしょう？","恋符　マスタースパーク","反魂蝶","禁忌　レーヴァテイン");
        Question q13 = new Question(R.drawable.touhou13,"リリース順に並び替えて、適当な物を選びなさい。","東方永夜紗ー東方華映塚ー東方風神録","東方永夜抄ー東方風神録ー東方花映塚","東方花映塚ー東方永夜抄ー東方風神録");
        Question q14 = new Question(R.drawable.touhou14,"「紅　美鈴」なんと読む？","ほん　めいりん","くれない　めいりん","こう　めーりん");
        Question q15 = new Question(R.drawable.touhou15,"特別問題！　製作者の好きなキャラは？","霧雨　魔理沙","フランドール　スカーレット","ケンシロウ");
        Question q16 = new Question(R.drawable.sazaesann,"実際に、長谷川町子によって描かれたタラちゃんの妹の名前は？","ヒトデ","ホタテ","イルカ");
        Question q17 = new Question(R.drawable.benntou,"肖像画のベートーベンが不機嫌な理由は？","家政婦の料理がまずかったため。","寝不足のため。","そんな気分じゃなかったため");
        Question q18 = new Question(R.drawable.hana,"母の日に贈る花で有名な「カーネーション」。さて、黄色のカーネーションの花言葉は次のうちどれ？","軽蔑","家族愛","嫉妬");
        Question q19 = new Question(R.drawable.touhou16,"水の属性を持つ「中心」と言えば誰？","森近　霖之助","河城　にとり","キスメ");
        Question q20 = new Question(R.drawable.touhou17,"魔理沙が強力な魔法を使える理由は？（超難問）","魔法の森の幻覚作用がある茸","アリスが作ったポーションを魔理沙が間違えて飲んだから。","霊夢の作った秘薬を魔理沙が飲んだから。");




        question_list.add(q1);
        question_list.add(q2);
        question_list.add(q3);
        question_list.add(q4);
        question_list.add(q5);
        question_list.add(q6);
        question_list.add(q7);
        question_list.add(q8);
        question_list.add(q9);
        question_list.add(q10);
        question_list.add(q11);
        question_list.add(q12);
        question_list.add(q13);
        question_list.add(q14);
        question_list.add(q15);
        question_list.add(q16);
        question_list.add(q17);
        question_list.add(q18);
        question_list.add(q19);
        question_list.add(q20);
        // TODO [03] ここまで
    }

    // 問題の表示を始める
    private void startQuestion() {
        question_num = question_list.size();
        nextQuestion();
    }

    private void nextQuestion() {
        if (current >= question_num) {
            current = -1;
            // 次の問題がもう無い時
            // 結果画面に移動
            Intent i = new Intent(this, ResultActivity.class);
            i.putExtra("QUESTION", question_num);
            i.putExtra("CORRECT", correct_num);
            startActivity(i);
            // そのままにしておくと画面が積み重なっていくので終了させる
            finish();
            return;
        }
        // TODO [04] ここから
        status.setText(String.valueOf(current) + "問中"
                +String.valueOf(correct_num) + "問正解"
                +"残り"+String.valueOf(question_num-current)+"問");

        current_question=question_list.get(current);


        question.setText(current_question.question_text);
        image.setImageResource(current_question.image_id);

        String[]choises_text=current_question.getChoices();
        button1.setText(choises_text[0]);
        button2.setText(choises_text[1]);
        button3.setText(choises_text[2]);

        current=current+1;

        startTimeLimitThread();
        // TODO [04] ここまde
    }

    // ボタンがタップされた時に呼ばれるイベントリスナー
    public void click(View v) {
        // TODO [05] ここから
        String buttonText=((Button)v).getText().toString();

        if(buttonText.equals(current_question.answer)){
            correct_num=correct_num+1;

        }
        nextQuestion();
        // TODO [05] ここまで
    }

    /**
     * 1問ごとの制限時間を管理するスレッドを起動する
     */
    private void startTimeLimitThread() {
//        rest_time = time * 100;
//        progress.setProgress(rest_time);
//        // このThreadが担当する問題番号
//        final int local_current = current;
//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                while (rest_time >= 0) {
//                    if (local_current != current) {
//                        // すでにボタンをタップして次の問題に進んでいる
//                        return;
//                    }
//                    try {
//                        // 10ミリ秒待機
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            rest_time -= 1;
//                            progress.setProgress(rest_time);
//                        }
//                    });
//                }
//                // まだ問題に解答していない場合
//                if (local_current == current) {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            nextQuestion();
//                        }
//                    });
//                }
//            }
//        };
//        t.start();
        shrinkStart();
    }

    /**
     * 問題を管理するクラス
     */
    class Question {
        /**
         * 画面に表示する画像のリソースID
         */
        private final int image_id;
        /**
         * 問題文として表示する文字列
         */
        private final String question_text;
        /**
         * 正解の答え
         */
        private final String answer;
        /**
         * 不正解の答え①
         */
        private final String wrong_1;
        /**
         * 不正解の答え②
         */
        private final String wrong_2;

        public Question(int image_id, String question_text, String answer, String wrong_1, String wrong_2) {
            this.image_id = image_id;
            this.question_text = question_text;
            this.answer = answer;
            this.wrong_1 = wrong_1;
            this.wrong_2 = wrong_2;
        }

        /**
         * シャッフルした問題の選択肢を返すメソッド
         */
        public String[] getChoices() {
            // ボタンの位置をランダムにする
            String choices_tmp[] = {answer, wrong_1, wrong_2};
            // 配列からリストへ
            List<String> list = Arrays.asList(choices_tmp);
            // リストをシャッフル
            Collections.shuffle(list);
            // リストをStringの配列にする
            return list.toArray(new String[3]);
        }
    }

    private void shrinkStart() {
        blank.startAnimation(getWidthAnimation(blank, gifProgressBar));
    }

    private void createCustomProgressBar() {
        blank = findViewById(R.id.blank);
        gifProgressBar = findViewById(R.id.gif_progressbar);

        ImageView imageView = (ImageView) findViewById(R.id.image_gif);
        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.raw.chirno_progress_material_iloveimg_cropped).into(target);
    }

    private WidthAnimation getWidthAnimation(View blank, final View gifProgressBar) {

        WidthAnimation widthAnime = new WidthAnimation(blank, 0, 320);
        widthAnime.setDuration(time * 1000);
        widthAnime.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                gifProgressBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.progressbar_background));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                gifProgressBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.progressbar_end_background));
                nextQuestion();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        return widthAnime;
    }
}
