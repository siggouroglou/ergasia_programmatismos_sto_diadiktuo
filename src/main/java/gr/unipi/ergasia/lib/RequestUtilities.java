package gr.unipi.ergasia.lib;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author siggouroglou@gmail.com
 */
public class RequestUtilities {

    public static String getRequestName(HttpServletRequest request, String path) {
        StringBuffer requestURL = request.getRequestURL();
//        if (request.getQueryString() != null) {
//            requestURL.append("?").append(request.getQueryString());
//        }
        String completeURL = requestURL.toString();
        int quoteIndex = completeURL.lastIndexOf("?");
        return completeURL.substring(completeURL.lastIndexOf(path) + path.length(), quoteIndex == -1 ? completeURL.length() : quoteIndex);
    }
}
