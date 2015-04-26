package grader.model.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * A Remote Method Invocation interface for fetching course rosters.
 * @author Quan Tran
 */
public interface Lookup extends Remote {

    /**
     * Fetches the roster for the given course as a map
     * of EMPLIDs to student names.
     * @param course the course to fetch the roster for
     * @return a map of EMPLIDs to student names
     * @throws RemoteException
     */
    Map<String, String> fetchRoster(String course) throws RemoteException;
}