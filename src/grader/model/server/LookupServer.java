package grader.model.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * A proxy for the CSC department class roster lookup server.
 * @author Quan Tran
 */
public class LookupServer implements Lookup {
    private final static String NAME = "lookup";
    private Map<String, String> roster;

    /**
     * Constructs a LookupServer with canned roster data.
     */
    public LookupServer() {
        roster = new HashMap<String, String>();
        roster.put("000000001", "Name");
    }

    /**
     * Fetches the roster for the given course as a map
     * of EMPLIDs to student names.
     * @param course the course to fetch the roster for
     * @return a map of EMPLIDs to student names
     */
    public Map<String, String> fetchRoster(String course) {
        if (course.equals("309"))
            return roster;
        else
            return null;
    }

    /**
     * Remote main method.
     * @param args arguments
     */
    public static void main(String[] args) {
        // check and set up security manager
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        // attempt lookup
        try {
            Lookup lookup = new LookupServer();
            Lookup stub = (Lookup) UnicastRemoteObject.exportObject(lookup, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(NAME, stub);
            System.out.println("Lookup bound");
        } catch (Exception e) {
            System.err.println("Lookup exception:");
            e.printStackTrace();
        }
    }
}
