package va4;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import com.jcraft.jsch.jce.Random;

public class EpuertoConfirmacion implements Callable{

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		//System.out.println(eventContext.getMessage().getClass().getCanonicalName());
		ColocarOrden input = (ColocarOrden) eventContext.getMessage().getPayload();
		ConfirmacionOrden output = new ConfirmacionOrden();
		output.codigoResultado = 0;
		output.descripcionResultado = "OK";
		output.identificadorCompra = input.getIdentificadorCompra();
		java.util.Random r = new java.util.Random();
		output.identificadorReserva = String.valueOf(r.nextInt((5000000 - 1) + 1));
		return output;
	}

	
	
	

}
