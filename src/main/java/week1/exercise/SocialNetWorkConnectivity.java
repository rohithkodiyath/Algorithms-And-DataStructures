package week1.exercise;

import java.util.Arrays;

import week1.WeightedQuickUnion;

public class SocialNetWorkConnectivity {
	
	WeightedQuickUnion wQuickUnion;
	
	public SocialNetWorkConnectivity(int number){
		wQuickUnion = new WeightedQuickUnion(number);
	}
	
	
	public boolean connectFriends(int a,int b) {
		wQuickUnion.union(a, b);
		return wQuickUnion.getNumberOfConnectionsComponents() == 1;
	}
	
	public static void main(String[] args) {
		SocialNetWorkConnectivity socialCOnnectivity = new SocialNetWorkConnectivity(3);
		System.out.println(socialCOnnectivity.connectFriends(0, 2));
		System.out.println(socialCOnnectivity.connectFriends(0, 1));
		System.out.println(socialCOnnectivity.connectFriends(2, 1));
		
	}
}
