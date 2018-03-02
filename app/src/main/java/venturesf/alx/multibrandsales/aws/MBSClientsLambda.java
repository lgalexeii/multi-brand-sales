package venturesf.alx.multibrandsales.aws;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

import venturesf.alx.getclientsaws.vo.ClientsRequest;
import venturesf.alx.getclientsaws.vo.ClientsResponse;

/**
 * Created by B942272 on 01/03/2018.
 */

public interface MBSClientsLambda {
    @LambdaFunction
    ClientsResponse mbsClients(ClientsRequest request);
}
