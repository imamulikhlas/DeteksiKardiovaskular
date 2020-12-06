package com.agung.deteksikardiovaskular.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.agung.deteksikardiovaskular.R;
import com.agung.deteksikardiovaskular.model.ModelCekUser;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class AddPatientActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;

    private EditText inputUmur;

    private RadioGroup radioGroupGender;
    private RadioButton radioButtonGender;
    private RadioGroup radioGroupAngina;
    private RadioButton radioButtonAngina;

    private Spinner inputChestPT;
    private Spinner inputRest_ecg;
    private Spinner inputST_slope;
    private Spinner inputThalassemia;

    private EditText inputRestingBP;
    private EditText inputFastingBS;
    private EditText inputCholesterol;
    private EditText inputMaxHeartRate;
    private EditText inputStDepression;
    private EditText inputMajorVessels;

    Interpreter tflite;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_patient);

        inputUmur= findViewById(R.id.input_umur);

//        Input Gender
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioGroupAngina = findViewById(R.id.radioGroupAngina);

//        Input for chest paint type
        inputChestPT = findViewById(R.id.chestPainType);
        ArrayAdapter<CharSequence> adapterChestPT = ArrayAdapter.createFromResource(this, R.array.chestPainType, android.R.layout.simple_spinner_item);
        adapterChestPT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputChestPT.setAdapter(adapterChestPT);
        inputChestPT.setOnItemSelectedListener(this);


//         Input for REST ECG
        inputRest_ecg = findViewById(R.id.input_resting_electrocardiographic);
        ArrayAdapter<CharSequence> adapterInputRest_ecg = ArrayAdapter.createFromResource(this, R.array.rest_ecg, android.R.layout.simple_spinner_item);
        adapterInputRest_ecg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputRest_ecg.setAdapter(adapterInputRest_ecg);
        inputRest_ecg.setOnItemSelectedListener(this);

//          Input for ST Slope
        inputST_slope = findViewById(R.id.input_st_slope);
        ArrayAdapter<CharSequence> adapterST_slope = ArrayAdapter.createFromResource(this, R.array.st_slope, android.R.layout.simple_spinner_item);
        adapterST_slope.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputST_slope.setAdapter(adapterST_slope);
        inputST_slope.setOnItemSelectedListener(this);

//          Input for Thalasemia
        inputThalassemia = findViewById(R.id.input_thalassemia);
        ArrayAdapter<CharSequence> adapterThalassemia = ArrayAdapter.createFromResource(this, R.array.thalassemia, android.R.layout.simple_spinner_item);
        adapterThalassemia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputThalassemia.setAdapter(adapterThalassemia);
        inputThalassemia.setOnItemSelectedListener(this);

//  Input for regular input
        inputRestingBP = findViewById(R.id.input_restingBP);
        inputFastingBS = findViewById(R.id.input_fastingBS);
        inputCholesterol = findViewById(R.id.input_cholesterol);
        inputMaxHeartRate = findViewById(R.id.input_maximum_heart_rate);
        inputStDepression = findViewById(R.id.input_st_depression);
        inputMajorVessels = findViewById(R.id.input_major_vessels);

        //For Tensor Flow
        try {
            tflite = new Interpreter(loadModelFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Button btnCekPasien = findViewById(R.id.btn_cek_pasien);
//        btnCekPasien.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseApp.initializeApp(getApplication());
//                String name = inputName.getText().toString().trim();
//                String dateBirth = inputDateBirth.getText().toString().trim();
//                String gender = inputGender.getText().toString().trim();
//                String dateIssue = inputDateIssue.getText().toString().trim();
//                String restingBP = inputRestingBP.getText().toString().trim();
//                String fastingBS = inputFastingBS.getText().toString().trim();
//                String cholesterol = inputCholesterol.getText().toString().trim();
//                String restingEM = inputRestingEM.getText().toString().trim();
//                String maxHR = inputMaxHeartRate.getText().toString().trim();
//                String exerciseIA = inputExerciseIA.getText().toString().trim();
//                String stDepression = inputStDepression.getText().toString().trim();
//                String stSlope = inputStSlope.getText().toString().trim();
//                String majorVessels = inputMajorVessels.getText().toString().trim();
//                String thalassemia = inputThalassemia.getText().toString().trim();
//
//                int finalRestingBP = Integer.parseInt(restingBP);
//                int finalFastingBS = Integer.parseInt(fastingBS);
//                int finalCholesterol = Integer.parseInt(cholesterol);
//                int finalMaxHR = Integer.parseInt(maxHR);
//                int finalMajorVessels = Integer.parseInt(majorVessels);
//                float finalSTDepression = Float.parseFloat(stDepression);
//
//                //Dummy var
//                String chestPT = "a";
//
////                mFirebaseInstance = FirebaseDatabase.getInstance();
////                mDatabaseReference = mFirebaseInstance.getReference("KardiovaskularDetektor");
////                mDatabaseReference.child("cek_pasien").push().setValue(new ModelCekUser(name, dateBirth, dateIssue, chestPT, restingEM, exerciseIA, stSlope, thalassemia, finalRestingBP, finalFastingBS, finalCholesterol, finalMaxHR, finalMajorVessels, finalSTDepression, null)).addOnSuccessListener(AddPatientActivity.this, new OnSuccessListener<Void>() {
////                    @Override
////                    public void onSuccess(Void aVoid) {
////                        Toast.makeText(AddPatientActivity.this, "Data barang berhasil di simpan !", Toast.LENGTH_LONG).show();
////
////                    }
//                });
//            }
//        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        ----Jika ingin ada pop up setelah dipilih drop down menu, silahkan di uncomment----

//        textChestPT = parent.getItemAtPosition(position).toString();
//        textRest_ECG = parent.getItemAtPosition(position).toString();
//        textST_Slope = parent.getItemAtPosition(position).toString();
//        textThalasemia = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), textChestPT, Toast.LENGTH_SHORT).show();
//        Toast.makeText(parent.getContext(), textRest_ECG, Toast.LENGTH_SHORT).show();
//        Toast.makeText(parent.getContext(), textST_Slope, Toast.LENGTH_SHORT).show();
//        Toast.makeText(parent.getContext(), textThalasemia, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void cekPasien(View view) {
        //For DropDown List
        String textChestPT = inputChestPT.getSelectedItem().toString();
        String textRest_ECG = inputRest_ecg.getSelectedItem().toString();
        String textST_Slope = inputST_slope.getSelectedItem().toString();
        String textThalasemia = inputThalassemia.getSelectedItem().toString();

        //For Radio Button
        int Gender = radioGroupGender.getCheckedRadioButtonId();
        radioButtonGender = (RadioButton) findViewById(Gender);
        String textGender = radioButtonGender.getText().toString();

        int Angina = radioGroupAngina.getCheckedRadioButtonId();
        radioButtonAngina = (RadioButton) findViewById(Angina);
        String textAngina = radioButtonAngina.getText().toString();

        //For regular input
        String textUmur = inputUmur.getText().toString();
        String textRestBP = inputRestingBP.getText().toString();
        String textFastingBS = inputFastingBS.getText().toString();
        String textCholestrol = inputCholesterol.getText().toString();
        String textMaxHeartRate = inputMaxHeartRate.getText().toString();
        String textSTDepression = inputStDepression.getText().toString();
        String textMajorVessel = inputMajorVessels.getText().toString();

        //Convert user input into model input
        String chest_pain_type_atypical_angina = "0";
        String chest_pain_type_nonAnginal_pain = "0";
        String chest_pain_type_typical_angina = "0";
        String fasting_blood_sugar_lower_than_120mg_ml = "0";
        String rest_ecg_left_ventricular_hypertrophy = "0";
        String rest_ecg_normal = "0";
        String exercise_induced_angina_yes = "0";
        String st_slope_flat = "0";
        String st_slope_upsloping = "0";
        String thalassemia_fixed_defect = "0";
        String thalassemia_normal= "0";
        String thalassemia_reversable_defect = "0";

        String result = "Negative";

        if (textGender.equals("Female")) {
            textGender = "0";
        }else {
            textGender = "1";
        }

        if (textChestPT.equals("atypical angina")){
            chest_pain_type_atypical_angina = "1";
        } else if(textChestPT.equals("typical angina")){
            chest_pain_type_typical_angina = "1";
        }else if(textChestPT.equals("non-anginal pain")) {
            chest_pain_type_nonAnginal_pain = "1";
        }

        if (Integer.parseInt(textFastingBS)<120){
            fasting_blood_sugar_lower_than_120mg_ml = "1";
        }

        if (textRest_ECG.equals("normal")) {
            rest_ecg_normal = "1";
        }else if(textRest_ECG.equals("left ventricular hypertrophy")){
            rest_ecg_left_ventricular_hypertrophy = "1";
        }

        if (textAngina.equals("Yes")){
            exercise_induced_angina_yes = "1";
        }

        if (textST_Slope.equals("upsloping")){
            st_slope_upsloping = "1";
        } else if(textST_Slope.equals("flat")){
            st_slope_flat = "1";
        }

        if (textThalasemia.equals("normal")){
            thalassemia_normal = "1";
        } else if(textThalasemia.equals("fixed defect")){
            thalassemia_fixed_defect = "1";
        } else if(textThalasemia.equals("reversable defect")){
            thalassemia_reversable_defect = "1";
        }



        String[] inputForTF= {textUmur, textRestBP, textCholestrol, textMaxHeartRate, textSTDepression, textMajorVessel, textGender, chest_pain_type_atypical_angina, chest_pain_type_nonAnginal_pain,
                chest_pain_type_typical_angina, fasting_blood_sugar_lower_than_120mg_ml, rest_ecg_left_ventricular_hypertrophy, rest_ecg_normal, exercise_induced_angina_yes, st_slope_flat,
                st_slope_upsloping, thalassemia_fixed_defect, thalassemia_normal, thalassemia_reversable_defect};

        // Mencari hasil kalkulasi
        if (doInference(inputForTF) > 0.5){
            result = "Positive";
        }

        Log.d("clicked", result);

        //--Variabel yang dimasukan ke DB:
        //textChestPT
        //textRest_ECG
        //textST_Slope
        //textThalasemia
        //textGender
        //textAngina
        //textUmur
        //textRestBP
        //textFastingBS
        //textCholestrol
        //textMaxHeartRate
        //textSTDepression
        //textMajorVessel
    }

    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = this.getAssets().openFd("model.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    public float doInference(String[] value){
        float[] inputVal = new float[19];
        inputVal[0] = Float.valueOf(value[0]);
        inputVal[1] = Float.valueOf(value[1]);
        inputVal[2] = Float.valueOf(value[2]);
        inputVal[3] = Float.valueOf(value[3]);
        inputVal[4] = Float.valueOf(value[4]);
        inputVal[5] = Float.valueOf(value[5]);
        inputVal[6] = Float.valueOf(value[6]);
        inputVal[7] = Float.valueOf(value[7]);
        inputVal[8] = Float.valueOf(value[8]);
        inputVal[9] = Float.valueOf(value[9]);
        inputVal[10] = Float.valueOf(value[10]);
        inputVal[11] = Float.valueOf(value[11]);
        inputVal[12] = Float.valueOf(value[12]);
        inputVal[13] = Float.valueOf(value[13]);
        inputVal[14] = Float.valueOf(value[14]);
        inputVal[15] = Float.valueOf(value[15]);
        inputVal[16] = Float.valueOf(value[16]);
        inputVal[17] = Float.valueOf(value[17]);
        inputVal[18] = Float.valueOf(value[18]);

        float[][] outputval = new float[1][1];

        tflite.run(inputVal, outputval);

        float inferredValue = outputval[0][0];

        return inferredValue;

    }
}