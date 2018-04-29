package es.iesvjp.acadt;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

	/**
	 * @author Ana Arribas
	 *
	 */
	public class MiObjectOutputStream extends ObjectOutputStream{
		//constructor sin parámetros
		MiObjectOutputStream() throws IOException{
		super();
		}
		//constructor que recibe OutputStream
		public MiObjectOutputStream(OutputStream oos) throws IOException{
		super(oos);
		}
		//redefinición del método escribir cabecera para que no haga nada

		protected void writeStreamHeader() throws IOException
		{
		//no hacer nada
		}

		}


