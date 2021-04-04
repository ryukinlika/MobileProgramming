package id.ac.umn.week08a_29240;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rgJenis;
    private EditText etFileName, etText;

    private File tempDir, localDir, extDir, currDir;

    private Context context;
    private Button btnOpen;
    private static PopupMenu pickFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rgJenis = (RadioGroup) findViewById(R.id.rgJenis);
        etFileName = (EditText) findViewById(R.id.etNamaFile);
        etText = (EditText) findViewById(R.id.etText);

        tempDir = getCacheDir();
        localDir = getFilesDir();
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            extDir = getExternalFilesDir(null);
        }
        else {
            findViewById(R.id.rbExternal).setEnabled(false);
            extDir = null;
        }

        currDir = localDir;
        rgJenis.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String pilihan = ((RadioButton) findViewById(rgJenis.getCheckedRadioButtonId()))
                        .getText().toString();
                if(pilihan.equalsIgnoreCase("Temporary")) currDir = tempDir;
                else if(pilihan.equalsIgnoreCase("Internal")) currDir = localDir;
                else currDir = extDir;
            }
        });

        context = this;
        btnOpen = (Button) findViewById(R.id.btnOpen);
        pickFiles = new PopupMenu(context, btnOpen);
        pickFiles.getMenuInflater().inflate(R.menu.empty_menu, pickFiles.getMenu());
        pickFiles.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                etFileName.setText(item.getTitle().toString());
                //etFileName.setText("");
                //etText.setText("");
                readFile();
                return true;
            }
        });

    }

    public void openFile(View view) {
        File[] files = null;
        if(currDir != null) files = currDir.listFiles();
        if(files != null){
            int n = files.length;
            pickFiles.getMenu().clear();
            for(int i = 0; i < n; i++){
                pickFiles.getMenu().add(files[i].getName());
            }
            pickFiles.show();
            //readFile();
        }
        else{
            Toast.makeText(context, "Problem in accessing folder OR folder is empty", Toast.LENGTH_LONG).show();
        }
        
    }

    private void readFile() {
        if(etFileName.getText().toString().length() > 0){
            File file = new File(currDir, etFileName.getText().toString());
            String filecontent = "";
            try{
                InputStream inStream = new FileInputStream(file);
                if(inStream != null){
                    InputStreamReader isReader = new InputStreamReader(inStream);
                    BufferedReader bReader = new BufferedReader(isReader);
                    String rec = "";
                    StringBuilder sb = new StringBuilder();
                    while((rec = bReader.readLine())!= null){
                        sb.append(rec).append("\n");
                    }
                    inStream.close();
                    filecontent = sb.toString();
                    etText.setText(filecontent);
                    Log.e("e", filecontent);
                }
            } catch (FileNotFoundException e) {
                Toast.makeText(context,"File not found",
                        Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(context,"I/O errors",
                        Toast.LENGTH_LONG).show();
            }
        }
    }


    public void saveFile(View view) {
        String nFile = etFileName.getText().toString();
        String textContent = etText.getText().toString();
        if(nFile.length() > 0 && textContent.length() > 0 && currDir != null){
            File file = new File(currDir, nFile);
            try{
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
                writer.write(textContent);
                writer.close();
                Toast.makeText(this, "File is saved", Toast.LENGTH_LONG).show();
            }
            catch (FileNotFoundException e){
                Toast.makeText(this, "File not found", Toast.LENGTH_LONG).show();
            }
            catch (IOException e){
                Toast.makeText(this, "I/O Error", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void deleteFile(View view) {
        if(etFileName.getText().toString().length() > 0) {
            boolean success = false;
            if( currDir != null && currDir == localDir){
                success = context.deleteFile(etFileName.getText()
                        .toString());
            } else {
                success = new File(currDir, etFileName.getText().toString())
                        .delete();
            }
            if (success) Toast.makeText(context, "File has been successfully deleted",
                    Toast.LENGTH_LONG).show();
            else Toast.makeText(context, "File deletion failed",
                    Toast.LENGTH_LONG).show();
            etFileName.setText("");
            etText.setText("");
        }
    }

    public void clearText(View view) {
        etText.setText("");
        etFileName.setText("");
    }

    public void exitApp(View view) {
        finishAffinity();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        File[] tempFiles = tempDir.listFiles();
        for(File tempFile : tempFiles){
            if(tempFile.isFile()) tempFile.delete();
        }
    }

}