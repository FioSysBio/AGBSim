import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

//import jep.JepConfig;
//import jep.MainInterpreter;
//import jep.NDArray;
//import jep.PyConfig;
//import jep.SharedInterpreter;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import jep.Interpreter;
//
public class TesteUiPythonMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("acbm.service@gmail.com",
                                "ypczrmajwtxozsan");
                    }
                });

        /** Ativa Debug para sessão */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("acbm.service@gmail.com"));
            //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                    .parse("gianninimail@gmail.com, professor.thiago.ramos@gmail.com");

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Enviando email com JavaMail");//Assunto
            message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
            /**Método para enviar a mensagem criada*/
            Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
//    public static void main(String[] args) throws IOException, InterruptedException {
//
//        String pathPython = "/softwares/anaconda3/bin/python ";
//        String command = pathPython +  " /home/thiago/projetos/fiocruz/acbm-service/src/main/resources/static/script.py";
////                "print(calculate_average(20, 2))\"";
//
////        String command = "/softwares/anaconda3/bin/python --version"; //\"import my_module; print(my_module.square_root(25))\"";
//
//        Process process = Runtime.getRuntime().exec(command);
//        process.waitFor();
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//        }
//    }
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//
//        File pythonHome = new File("/softwares/anaconda3");
//        PyConfig conf = new PyConfig();
//        conf.setPythonHome(pythonHome.getAbsolutePath());
//        MainInterpreter.setInitParams(conf);
//
//        try (Interpreter interp = new SharedInterpreter()) {
//            interp.exec("from java.lang import System");
//            interp.exec("s = 'Testes da integração com o Python'");
//            interp.exec("System.out.println(s)");
//            interp.exec("print(s)");
//            interp.exec("print(s[1:-1])");
//        }
//
//
//        try (Interpreter interp = new SharedInterpreter()) {
//
//
//            interp.exec("import numpy as np");
//            interp.exec("from cobra.io import load_model, load_json_model, save_json_model, load_matlab_model, save_matlab_model, read_sbml_model, write_sbml_model");
//
//            String[] metabolites = {"GLCNtex", "GLCNtex"};
//            Double[] teste = {-1.0, -1.0};
//            String t = "iJO1366.json";
//
//            interp.set("reactions", metabolites);
//            interp.set("directions", teste);
//            interp.set("metabolic_model", t);
//            interp.runScript("./src/main/resources/static/substrateFinder_v2.py");
//
//            double[] results = (double[]) ((NDArray) interp.getValue("result")).getData();
//
//            Arrays.stream(results).forEach(System.out::println);
//        }
//    }
//}
