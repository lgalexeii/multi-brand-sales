package venturesf.alx.multibrandsales.aws;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

import venturesf.alx.vo.ClientsRequest;
import venturesf.alx.vo.ClientsResponse;

/**
 * Created by B942272 on 01/03/2018.
 */

public interface MBSClientsLambda {
    @LambdaFunction
    ClientsResponse mbsClients(ClientsRequest request);

    @LambdaFunction
    ClientsResponse mbsClientImage(ClientsRequest request);
}
