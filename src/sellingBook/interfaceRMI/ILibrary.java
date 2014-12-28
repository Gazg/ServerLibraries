package sellingBook.interfaceRMI;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ILibrary extends Remote{
	public IBook getBook(Long ISBN) throws RemoteException;
	public IBook getBook(String title) throws RemoteException;
	public List<IBook> getBooksThatContain(String title) throws RemoteException;
	public List<IBook> getBooksThatBeginWith(String title) throws RemoteException;
	public IBook removeBook(Long ISBN) throws RemoteException;
	public List<IBook> getBooksByAuthor(String author) throws RemoteException;
	public void addBooks(List<IBook> books) throws RemoteException;
	public void addBook(IBook book) throws RemoteException;
	public void addBook(Long isbn,String title,String author,Double price) throws RemoteException;
	public List<IBook> getAllBooks() throws RemoteException;
}
