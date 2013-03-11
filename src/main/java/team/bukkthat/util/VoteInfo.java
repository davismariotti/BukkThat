package team.bukkthat.util;

public class VoteInfo {

	String n = "";
	int v;
	public VoteInfo(String s, int i) {
		n = s;
		v = i;
	}
	
	public String getName() {
		return n;
	}
	public int getVotes() {
		return v;
	}
	
}
