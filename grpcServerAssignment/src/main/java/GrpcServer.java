import java.io.IOException;

import User.UserService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
//import io.grpc.netty.shaded.io.netty.util.concurrent.GlobalEventExecutor;

public class GrpcServer {

	 public static void main(String args[]) throws IOException, InterruptedException {
		 
		 System.out.println("starting GRPC Server");
		// Object exec = GlobalEventExecutor.INSTANCE;
		 Server server = ServerBuilder.forPort(9090).addService(

				 new UserService()).build();
		 
		 server.start();
		 System.out.println("server started at "+ server.getPort());
	        server.awaitTermination();
	 }
}