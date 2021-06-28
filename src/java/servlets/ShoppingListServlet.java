package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShoppingListServlet extends HttpServlet {    
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
       HttpSession session = request.getSession( false );
       if( session == null || session.getAttribute( "username" ) == null )
       {    
           getServletContext().getRequestDispatcher( "/WEB-INF/register.jsp" ).forward(request, response);
           return;
       }
       
       String action = request.getParameter( "action" );
        if( action != null && action.equals( "logout" ) )
        {
            session.invalidate();
            getServletContext().getRequestDispatcher( "/WEB-INF/register.jsp" ).forward(request, response);
            return;
        }
       
       getServletContext().getRequestDispatcher( "/WEB-INF/shoppingList.jsp" ).forward(request, response);
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        String action = request.getParameter( "action" );
        if( action == null )
            return;
        
        if( action.equals( "register" ) )
        {
            HttpSession session = request.getSession();
            session.setAttribute( "username", request.getParameter( "username" ) );
            response.sendRedirect( "ShoppingList" );
            return;
        }

        HttpSession session = request.getSession( false );
        if( session == null || session.getAttribute( "username" ) == null )
            return;
        
        ArrayList<String> shoppingList = (ArrayList<String>)session.getAttribute( "shoppingList" );
        if( shoppingList == null )
            shoppingList = new ArrayList<>();
        
        if( action.equals( "add" ) )
            shoppingList.add( request.getParameter( "addItem" ) );
        else if( action.equals( "delete" ) )
        {
            int item = Integer.parseInt( request.getParameter( "shoppingList" ) );
            /*if( item == null || item.isEmpty() )
                return;

            boolean foundItem = false;
            for( String itemSearch : shoppingList )
            {
                if( itemSearch.equals( item ) )
                    foundItem = true;
            }
            
            if( !foundItem )
                return;*/
            
            shoppingList.remove( item );
        }
        
        session.setAttribute( "shoppingList", shoppingList );      
        getServletContext().getRequestDispatcher( "/WEB-INF/shoppingList.jsp" ).forward(request, response);
    }
}