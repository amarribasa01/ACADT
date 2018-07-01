package es.iesvjp.acadt;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

public class ConversorFechas extends PropertyEditorSupport {

   @Override
   public void setAsText(String text) throws IllegalArgumentException {
      setValue(LocalDate.parse(text));
      
   }

   @Override
   public String getAsText() {
      LocalDate value = LocalDate.parse(String.valueOf(getValue()));
      return (value != null ? value.toString() : "");
   }

}
