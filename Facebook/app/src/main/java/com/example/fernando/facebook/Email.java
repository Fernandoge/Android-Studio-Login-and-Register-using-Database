package com.example.fernando.facebook;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
// en el primer parametro se coloca un objeto Mail para uno o mas destinatarios del mail
public class Email extends AsyncTask<Email.Correo,Void,Void> {

    private final String user;
    private final String pass;
    private final Activity activity;

    //user y pass son el user y clave del que envia el correo
    public Email(String user, String pass,Activity a) {
        super();
        this.user=user;
        this.pass=pass;
        this.activity = a;
    }

    @Override
    protected Void doInBackground(Correo... mails) {
        Properties props = new Properties();
        //usar autenticación mediante usuario y clave
        props.put("mail.smtp.auth", "true");
        // permite conectar de manera segura al servidor
        props.put("mail.smtp.starttls.enable", "true");
        //Hace referencia al servidor de envio que en este caso es de Google
        props.put("mail.smtp.host", "smtp.gmail.com");
        //Hace referencia al  puerto del servidor de envio que en este caso es de Google
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pass);
                    }
                });
        for (final Correo mail:mails) {

            try {

                Message message = new MimeMessage(session);
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(mail.to));
                message.setSubject(mail.subject);
                message.setText(mail.content);

                Transport.send(message);

                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(activity,"Mensaje enviado a " + mail.to,Toast.LENGTH_SHORT).show();
                    }
                });


            } catch (final MessagingException e) {

                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(activity,"Error al enviar "+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                Log.d("Email", e.getMessage());
            }
        }
        return null;
    }
    // Esta clase construye el mail Mail es el nombre puede ser cualquier nombre
    public static class Correo{
        private final String subject;
        private final String content;
        private final String to;

        public Correo(String to, String subject, String content){
            this.subject=subject;
            this.content=content;
            this.to=to;
        }
    }
}
