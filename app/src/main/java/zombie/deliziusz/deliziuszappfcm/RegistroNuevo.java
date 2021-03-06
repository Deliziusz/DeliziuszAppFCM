package zombie.deliziusz.deliziuszappfcm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroNuevo extends AppCompatActivity {
    private static final String TAG =" ";
    TextView miIniciarSesion;
    EditText mCorreo, mClave;
    Button mRegister;

    //variables Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_nuevo);

        mAuth = FirebaseAuth.getInstance();

        miIniciarSesion = (TextView) findViewById(R.id.txt_iniciar_sesion);
        mCorreo = (EditText) findViewById(R.id.txt_correo);
        mClave =(EditText) findViewById(R.id.txt_password);
        mRegister = (Button) findViewById(R.id.btn_register);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = mCorreo.getText().toString();
                password = mClave.getText().toString();
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(RegistroNuevo.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //Sign in success
                                    Intent intent = new Intent(RegistroNuevo.this, InicioSesionExitoso.class);
                                    startActivity(intent);
                                    Toast.makeText(RegistroNuevo.this, "Registrado correctamente",
                                            Toast.LENGTH_LONG).show();
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    Toast.makeText(RegistroNuevo.this, "Error al iniciar sesion",
                                            Toast.LENGTH_LONG).show();
                                    updateUI(null);
                                }
                            }
                        });
            }
        });

    }
    private void updateUI(FirebaseUser user) {
        if(user != null){
            Intent intent = new Intent(RegistroNuevo.this, InicioSesionExitoso.class);
            startActivity(intent);
            finish();
        }
    }

    }
