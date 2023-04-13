package br.fiocruz.procc.acbmservice.service;//

import br.fiocruz.procc.acbmservice.domain.Environment;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;

@Service
public class RunService {

	private static int cont = 0;

	Thread t;

    public void execute(String pathLogFile) {
        // create simple Environment object
        Environment e1 = new Environment(pathLogFile);
    	e1.initialize();
		try {
			e1.addEntity();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        runGui(e1);
    }

    //visual version
    private void runGui(Environment environment){

        t = new Thread(new Runnable() {


			@Override
			public void run() {
				cont++;
				int num = cont;
				while( true ) {

		            try {

						Color c1 = new Color(235, 245, 255);
						Color st = new Color(0, 78, 152);

						//TAMANHO DO QUADRO: 0, 0, environment.getDimX() , environment.getDimY()
						// move and draw object
						environment.draw();
						environment.actionCore();

						if (environment.getTicks() == environment.getTickslimit() || environment.getBacterias().isEmpty()){

							System.out.println("Simulação " + num + " terminou com sucesso!");

							return;
						}

						// TEXTO PARA MOSTRAR NO RESULTADO("time:  " + environment.ticks*environment.getTickTime() + " min", environment.getDimX() + 25, 25);
						System.out.println("time: " + environment.getTicks() * environment.getTickTime() + " min");

						for (int i = 0; i < environment.getBacteria_name().size(); i++) {
							Color c = new Color(environment.getBacteria_color().get(i).getRed(), environment.getBacteria_color().get(i).getGreen(), environment.getBacteria_color().get(i).getBlue());
							if (environment.getL_bac().get(i) == 0) {
								System.out.println("Desenho Oval: "
												+ "Coord x1: " + environment.getDimX() + 25
												+ " / Coord y1" + 25 * ( i + 2 )
												+ " / Largura: " + 10
												+ " / Altura: " + 10
												+ " / Cor RGB: " + c.getRGB()
												+ " / Cor:  R=" + c.getRed() + ", G=" + c.getGreen() + ", B=" + c.getBlue()
								);
							} else {
								System.out.println("Desenho Retângulo Arredondado: "
										+ "Coord x1: " + environment.getDimX() + 25
										+ " / Coord y1" + 25 * ( i + 2 )
										+ " / Largura: " + 12
										+ " / Altura: " + 6
										+ "/ Largura ARC: " + 80
										+ " / Altura ARC: " + 100
										+ " / Cor RGB: " + c.getRGB()
										+ " / Cor:  R=" + c.getRed() + ", G=" + c.getGreen() + ", B=" + c.getBlue()
								);
							}
							System.out.println(environment.getBacteria_name().get(i));
							System.out.println(environment.getBacteria_conc().get(i).toString() + " (g/L)");
						}

						int offset = (environment.getBacteria_name().size()+2)*25+8;

						for (int i = 0; i < environment.getMetabolite_name().size(); i++) {
							Color c = new Color(environment.getMetabolite_color().get(i).getRed(), environment.getMetabolite_color().get(i).getGreen(), environment.getMetabolite_color().get(i).getBlue());
							System.out.println("Desenho Retângulo: "
									+ "Coord x1: " + environment.getDimX() + 29
									+ " / Coord y1" + offset + 25 * ( i + 1 )
									+ " / Largura: " + 2
									+ " / Altura: " + 2
							);
							System.out.println(environment.getMetabolite_name().get(i));
							System.out.println(environment.getMetabolite_conc().get(i).toString() + " (g/L)");
						}

						Thread.yield();

					}
					finally {

					}
		        }
			}
		});

		t.start();
	}


}
