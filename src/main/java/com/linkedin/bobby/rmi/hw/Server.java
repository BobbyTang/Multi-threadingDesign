package com.linkedin.bobby.rmi.hw;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Hello {
	public String sayHello() throws RemoteException {
		System.out.println("Server.sayHello()");
		return "Hello client...";
	};

	public static void main(String[] args) {
		Server server = new Server();
		Hello2Impl hello2 = new Hello2Impl();
		int regPort = 2001;

		try {
			Hello stub = (Hello) UnicastRemoteObject.exportObject(server, 10001);
			Hello2 stub2 = (Hello2) UnicastRemoteObject.exportObject(hello2, 10002);


			LocateRegistry.createRegistry(regPort);
			
			Registry reg = LocateRegistry.getRegistry(regPort);
			reg.bind(Hello.class.getSimpleName(), stub);
			reg.bind(Hello2.class.getSimpleName(), stub2);
			
			System.out.println("Server is ready.");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
