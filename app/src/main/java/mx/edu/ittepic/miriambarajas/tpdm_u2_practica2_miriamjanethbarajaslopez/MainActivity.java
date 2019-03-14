package mx.edu.ittepic.miriambarajas.tpdm_u2_practica2_miriamjanethbarajaslopez;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nombre, telefono, fecha, descripcion;
    Spinner seguro;
    Button guardar;
    BaseDatos base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        base = new BaseDatos(this, "Aseguradora", null, 1);

        nombre = findViewById(R.id.nombre);
        telefono = findViewById(R.id.telefono);
        fecha = findViewById(R.id.fecha);
        descripcion = findViewById(R.id.descripcion);
        seguro = findViewById(R.id.seguro);
        guardar = findViewById(R.id.guardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampos();
            }
        });
    }

    private void validarCampos() {
        if (nombre.getText().toString().isEmpty()){
            Toast.makeText(this, "Por favor complete los datos", Toast.LENGTH_LONG).show();
            return;
        }if (telefono.getText().toString().isEmpty()){
            Toast.makeText(this, "Por favor complete los datos", Toast.LENGTH_LONG).show();
            return;
        }if (descripcion.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor complete los datos", Toast.LENGTH_LONG).show();
            return;
        }if (seguro.getSelectedItemPosition() == 0){
            Toast.makeText(this, "Seleccione tipo de seguro", Toast.LENGTH_LONG).show();
            return;
        }else{
            insertarPropietario();
        }
    }

    private void insertarPropietario() {
        int s = seguro.getSelectedItemPosition();
        SQLiteDatabase insert = base.getWritableDatabase();
        String consulta1 ="", consulta2="";
        switch (s){
            case 1:
                consulta1 = "INSERT INTO PROPIETARIO VALUES ('"+telefono.getText().toString()+"','"+nombre.getText().toString()+"','"+
                        fecha.getText().toString()+"')";
                consulta2 = "INSERT INTO PERSONA VALUES ('casa','"+descripcion.getText().toString()+"','"+fecha.getText().toString()+"',1,'"+telefono.getText().toString()+"')";

                insert.execSQL(consulta1);
                insert.execSQL(consulta2);
                insert.close();

                nombre.setText("");
                fecha.setText("");
                descripcion.setText("");
                telefono.setText("");

                mensaje("Exito", "Se pudo insertar");
                break;
            case 2:
                consulta1 = "INSERT INTO PROPIETARIO VALUES ('"+telefono.getText().toString()+"','"+nombre.getText().toString()+"','"+
                        fecha.getText().toString()+"')";
                consulta2 = "INSERT INTO PERSONA VALUES ('auto','"+descripcion.getText().toString()+"','"+fecha.getText().toString()+"',2,'"+telefono.getText().toString()+"')";

                insert.execSQL(consulta1);
                insert.execSQL(consulta2);
                insert.close();

                nombre.setText("");
                fecha.setText("");
                descripcion.setText("");
                telefono.setText("");

                mensaje("Exito", "Se pudo insertar");
                break;
            case 3:
                consulta1 = "INSERT INTO PROPIETARIO VALUES ('"+telefono.getText().toString()+"','"+nombre.getText().toString()+"','"+
                        fecha.getText().toString()+"')";
                consulta2 = "INSERT INTO PERSONA VALUES ('medico','"+descripcion.getText().toString()+"','"+fecha.getText().toString()+"',3,'"+telefono.getText().toString()+"')";

                insert.execSQL(consulta1);
                insert.execSQL(consulta2);
                insert.close();

                nombre.setText("");
                fecha.setText("");
                descripcion.setText("");
                telefono.setText("");
                break;
        }

    }

    private void mensaje(String titulo, String mensaje) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);

        a.setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", null)
                .show();
    }
}
