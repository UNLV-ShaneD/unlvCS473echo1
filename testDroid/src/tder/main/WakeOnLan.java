package tder.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class WakeOnLan {
	public void Wake(String macAddress)
	{
		try
		{
			// Prepare a socket and target to send data
			DatagramSocket serverSocket = new DatagramSocket();
			
			InetAddress ipAddress = InetAddress.getByName("192.168.1.113");
			int port = 9;
			
			// Construct the Magic Packet for Wake on Lan
			byte[] sendData = new byte[6+16*6+32];
			
			int j = 0;
			for (int i = 0; i < 6; ++i)
			{
				sendData[j++] = (byte)0xFF;
			}
			
			for (int i = 0; i < 16; ++i)
			{
				for (int k = 0; k < 6; k++)
				{
					String[] hex = macAddress.split("(\\:|\\-)");
					sendData[j++] = (byte)Integer.parseInt(hex[k], 16);
				}
			}
			
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
			serverSocket.send(sendPacket);
			
			serverSocket.close();
		}
		catch (UnknownHostException e)
		{
			
		}
		catch (SocketException e)
		{
			
		}
		catch (IOException e)
		{
			
		}
		
	}
}
