package es.iesvjp.acadt;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

	/**
	 * @author Ana Arribas
	 *
	 */
	public class MiObjectOutputStream extends ObjectOutputStream{
		//constructor sin par�metros
		MiObjectOutputStream() throws IOException{
		super();
		}
		//constructor que recibe OutputStream
		public MiObjectOutputStream(OutputStream oos) throws IOException{
		super(oos);
		}
		//redefinici�n del m�todo escribir cabecera para que no haga nada

		protected void writeStreamHeader() throws IOException
		{
		//no hacer nada
		}

		}


