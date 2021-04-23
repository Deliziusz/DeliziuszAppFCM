package zombie.deliziusz.deliziuszappfcm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class InicioSesionExitoso extends AppCompatActivity {

    //Instanciamos el boton en nuestra clase
    private Button btn_cerrar;
    //Firebase
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion_exitoso);

        btn_cerrar = (Button) findViewById(R.id.btn_cerrar_sesion);
        //Detecto al usuario actual
        mAuth = FirebaseAuth.getInstance();

        btn_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Para que el usuario cierre sesión
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(InicioSesionExitoso.this, MainActivity.class));
            }
        });
    }
}