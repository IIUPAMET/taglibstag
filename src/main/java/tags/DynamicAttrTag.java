package tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.TagSupport;

public class DynamicAttrTag extends TagSupport implements DynamicAttributes{
    private static final long serialVersionUID = 1L;

    private Map<String, Object> map = new HashMap<String, Object>();

    @Override
    public int doStartTag() throws JspException {
        try {
            StringBuffer buffer = new StringBuffer();
            buffer.append( "<ul>" );
            for( String name : map.keySet() ){
                buffer.append( "<li>" )
                        .append( name )
                        .append( " - " )
                        .append( map.get( name ) )
                        .append( "</li>" );
            }
            buffer.append( "</ul>" );

            pageContext.getOut().print( buffer.toString() );
        } catch(IOException ioException) {
            throw new JspException("Error: " + ioException.getMessage());
        }
        return SKIP_BODY;
    }

    public void setDynamicAttribute(String uri, String name, Object value)
            throws JspException {
        map.put(name, value);
    }
}