package com.tarjentonimss.user.imagenes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
        import java.io.FileOutputStream;
        import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

import android.Manifest;
import android.app.Activity;
        import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
        import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;
import android.view.View;
        import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.Button;
        import android.widget.Gallery;
        import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
@SuppressWarnings("deprecation")
public class MainActivity  extends Activity implements OnClickListener {
    ImageView principal;
    Button botonw, boton2, boton3,boton4;
    Integer confirmacion;

    private LinearLayout lytMain;

    Bitmap topo;

    int movil;
    static Integer densidadpantalla;
    private Integer[] mImageIds = {
            R.drawable.cowboyshombre,
            R.drawable.cowboysmujer,
            R.drawable.billshombre,
            R.drawable.billsmujer,
            R.drawable.cuarenhombre,
            R.drawable.cuarentmujer,
            R.drawable.denverhombre,
            R.drawable.denvermujer,
            R.drawable.dolphinshombtre,
            R.drawable.dophinsmujer,
            R.drawable.eagleshombre,
            R.drawable.eaglesmujer,
            R.drawable.greenhombre,
            R.drawable.greenmujer,
            R.drawable.lionshombre,
            R.drawable.lionsmujer,
            R.drawable.panterashombre,
            R.drawable.panterasmujer,
            R.drawable.patriotshombre,
            R.drawable.patriotsmujuer,
            R.drawable.pitburghombre,
            R.drawable.patriotsmujuer,
            R.drawable.raidershombre,
            R.drawable.raidersmujer,
            R.drawable.santoshombre,
            R.drawable.santosmujer






    };

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad);
        densidadpantalla = (Integer) getResources().getDisplayMetrics().densityDpi;
        principal = (ImageView) findViewById(R.id.imageView1);
        botonw = (Button) findViewById(R.id.button1);
        botonw.setOnClickListener(this);
        boton2 = (Button) findViewById(R.id.button2);
        boton2.setOnClickListener(this);
        boton3 = (Button) findViewById(R.id.button3);
        boton3.setOnClickListener(this);
        boton4 = (Button) findViewById(R.id.button4);
        boton4.setOnClickListener(this);
        //principal.setOnClickListener((OnClickListener) this);


        Gallery galeria = (Gallery) findViewById(R.id.gallery1);
        galeria.setAdapter(new ImageAdapter(this));

        galeria.setOnItemClickListener(new OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // TODO Auto-generated method stub
                //Toast.makeText(Wallpaper.this, "topo" + position, Toast.LENGTH_SHORT).show();

                principal.setImageResource(mImageIds[position]);
                movil = mImageIds[position];
                confirmacion = position;
            }
        });
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.button1:


                if (confirmacion != null) {
                    AlertDialog.Builder builderii = new AlertDialog.Builder(this);
                    builderii.setTitle("Wallpaper");
                    builderii.setMessage("Estas seguro");
                    builderii.setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface builerii, int id) {

                            Bitmap topo = BitmapFactory.decodeStream(getResources().openRawResource(movil));
                            try {
                                getApplicationContext().setWallpaper(topo);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            Toast.makeText(MainActivity.this, "Se ha cambiado el Wallpaper", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builderii.setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface builerii, int id) {
                            Toast.makeText(MainActivity.this, "No se han hecho cambios", Toast.LENGTH_SHORT).show();

                        }
                    });

                    builderii.show();
                } else {
                    Toast.makeText(MainActivity.this, "Selecciona un Wallpaper", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.button2: {
                if (confirmacion != null) {


                    //Se carga la imagen que se quiere compartir
                    Bitmap bitmap = ((BitmapDrawable) principal.getDrawable()).getBitmap();
                    //Se guarda la imagen en la SDCARD

                    String fileName = movil + "" + ".jpg";
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                    File ExternalStorageDirectory = Environment.getExternalStorageDirectory();

                    File file = new File(ExternalStorageDirectory + File.separator + fileName);
                    FileOutputStream fileOutputStream = null;
                    try {
                        file.createNewFile();
                        fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(bytes.toByteArray());
                    } catch (IOException e) {

                    } finally {
                        if (fileOutputStream != null) {

                            Intent intento = new Intent(Intent.ACTION_SEND);
                            intento.setType("image/*");
                            Uri bmpUri = Uri.parse(file.getPath());
                            intento.putExtra(Intent.EXTRA_TEXT, "¿Me ayudas a adivinar quien es este personaje?" + Html.fromHtml("<br />") +
                                    "Descubre mas en Personajes Mexicanos Quiz " + Html.fromHtml("<br />") +
                                    "https://play.google.com/store/apps/details?id=com.toposdeus.personajesmexicanos");
                            intento.putExtra(
                                    Intent.EXTRA_STREAM,
                                    bmpUri);
                            startActivity(Intent.createChooser(intento,
                                    "Preguntale a tus amigos "));
                        }
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Selecciona un Wallpaper", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.button3:{
                if (confirmacion != null) {

                    try
                    {
                        principal.buildDrawingCache();
                        Bitmap bm=principal.getDrawingCache();
                        Intent localIntent = new Intent(Intent.ACTION_SEND);
                        String paramString1 = Integer.toString(movil);
                        //Bitmap topo2 = BitmapFactory.decodeStream(getResources().openRawResource(movil));
                        FileOutputStream localFileOutputStream = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/the killers" + paramString1 + ".jpeg");

                        bm.compress(Bitmap.CompressFormat.JPEG, 0, localFileOutputStream);
                        localFileOutputStream.close();

                        localIntent.setType("image/jpeg");
                        localIntent.putExtra(Intent.EXTRA_SUBJECT, "caricaturas");
                        localIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + Environment.getExternalStorageDirectory().getPath() + "/CARICA" + paramString1 + ".jpeg"));

                        Toast.makeText(MainActivity.this, "Se ha guardado el Wallpaper en " + Uri.parse( Environment.getExternalStorageDirectory().getPath()), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    catch (Exception localException)




                    { }
                }else
                {		 Toast.makeText(MainActivity.this, "Selecciona un Wallpaper", Toast.LENGTH_SHORT).show();
                }
                break;


            }
            case R.id.button4:{
                if (confirmacion !=null){
                    Intent intentae = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sntss.org.mx/requerimientos/leyes-y-reglamentos-gfee"));
                    startActivity(intentae);
                    break;

                }
            }
        }

    }
}


