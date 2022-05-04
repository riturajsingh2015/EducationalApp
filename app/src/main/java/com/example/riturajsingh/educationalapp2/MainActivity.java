package com.example.riturajsingh.educationalapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void submitAnswers(View v) ///Method invoked when submit Button is Clicked/////////////////////
    {

        ///////////////////////// l denotes local Variable///////////////
        int lCountCorrectAnswer=0;
        ///////////////////Get Reference To Parent Linear Layout containing all the child Elements
        LinearLayout  lParentLinearLayout=findViewById(R.id.ParentLinearLayout);
        //////////////////Variable contains the Choice Made By user Per Question//////////////////////
        String lChoiceMade="";
        //////////////////Variable contains the Answer to Corresponding Question//////////////////////
        String lAnswer="";

        ////////Handy Note - ParentLinearLayout contains ChildLinearLayouts (containing Questions)
        /// & AnswerViews Containing Answer Choices
        /// /////////////

        ////////////////This variable helps in iterating over each of the ChildLinearLayouts containing Questions//////////////////////
        int lChildLinearLayoutIndex;////Index of ChildLinearLayout goes from 2-11 /////

        for (lChildLinearLayoutIndex=2;lChildLinearLayoutIndex<=11;lChildLinearLayoutIndex++)
        {
            ////////////////Generate Answer for the Question at Index//////////////
            lAnswer = getAnswerOfQuestion(lChildLinearLayoutIndex);

            ///////////////This varibale Gets Reference Of ChildLinearLayout at Correspoding Index ///////////////////////////////////////////
            LinearLayout lChildLinearLayout = (LinearLayout) lParentLinearLayout.getChildAt(lChildLinearLayoutIndex);
            ///////////////This varibale Gets reference of the AnswerChoice View at index 2 of each ChildLinearLayout
            View lanswerChoiceView = lChildLinearLayout.getChildAt(2);

            /////////////////////Check if Answer Choice View is of type RadioGroup///////////////
            if (lanswerChoiceView instanceof RadioGroup) {
                ///Since Answer Choice View is of Type RadioGroup Cast it to RadioGroup so that  we get access to RadioGroup functionality
                RadioGroup childRadioGroup = (RadioGroup) lanswerChoiceView;

                ////Get Checked RadioButton Id  //////
                int checkedButtonId = childRadioGroup.getCheckedRadioButtonId();
                //////////If checkedButtonId !=-1 then it means the RadioButton is checked
                if (checkedButtonId != -1) {
                    RadioButton checkedRadioButton = findViewById(checkedButtonId);
                    ///Store String equivalent of ChoiceMade on Checked RadioButton into varible//////
                    lChoiceMade = checkedRadioButton.getText().toString();
                }
            }
            /////////////////////Check if Answer Choice View is of type EditText///////////////

            if (lanswerChoiceView instanceof EditText) {
                ///Since Answer Choice View is of Type EditText Cast it to EditText so that  we get access to EditText functionality
                EditText childEditText = (EditText) lanswerChoiceView;
                ///Store String equivalent of ChoiceMade on EditText into varible//////
                lChoiceMade = childEditText.getText().toString();
            }

            /////////////////////Check if Answer Choice View is of type CheckBox///////////////
            if (lanswerChoiceView instanceof CheckBox)
            {
                String tempAnswerString = "";
                for (int i = 2; i <= 5; i++) ///Answer choices for CheckBox vary from 2-5 so iterate over each
                {

                    CheckBox c = (CheckBox) lChildLinearLayout.getChildAt(i);////Get the Each CheckBox by iterating over it
                    if (c.isChecked())/////Check if CheckBox is Checked
                        tempAnswerString += c.getText();///If Checked then concatenate all the checked CheckBox strings into one
                }
                ///Store String equivalent of ChoiceMade on CheckBoxes into varible//////
                lChoiceMade = tempAnswerString;
            }


            /////If ChoiceMde matches the Answer , then increment lCountCorrectAnswer
            if (lChoiceMade.equals(lAnswer))
                lCountCorrectAnswer++;
        }

        ///////////////At Last when we have iterated over all the Questions then show a Toast Message Displaying your score////
        Toast.makeText(this, "Your Score is "+lCountCorrectAnswer, Toast.LENGTH_LONG).show();
    }


///Method Return the Answer of the Question at particular index////////////
    private String getAnswerOfQuestion(int i)
    {
        //Refer to Strings stored in strings.xml to generate answers/////////////
        String answer="";

            if(i==2)
                answer=getString(R.string.question1Answer);

            else if(i==3)
                answer=getString(R.string.question2Answer);

            else if(i==4)
                answer=getString(R.string.question3Answer);

            else if(i==5)
                answer=getString(R.string.question4Answer);

                ////Concatenate Answer Strings for CheckBox Questions
            else if(i==6)
                answer=getString(R.string.question5Answer1) + getString(R.string.question5Answer2);

            else if(i==7)
                answer=getString(R.string.question6Answer);

            else if(i==8)
                answer=getString(R.string.question7Answer);

            else if(i==9)
                answer=getString(R.string.question8Answer);

            else if(i==10)
                answer=getString(R.string.question9Answer);

            else
                answer=getString(R.string.question10Answer);

        return answer;
    }




}

