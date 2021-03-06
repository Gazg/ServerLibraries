package sellingBook;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sellingBook.interfaceRMI.IBook;
import sellingBook.interfaceRMI.ILibraries;
import sellingBook.interfaceRMI.ILibrary;

public class Libraries extends UnicastRemoteObject implements ILibraries{

	ArrayList<ILibrary> libraries ;
	int index;

	public Libraries() throws RemoteException {
		super();
		index = 0;
		libraries = new ArrayList<ILibrary>();
	}

	@Override
	public synchronized void subscribe(ILibrary lib) throws RemoteException {
		libraries.add(lib);

		System.out.println("lib add to the libraries");
	}

	@Override
	public synchronized void unsubscribe(ILibrary lib) throws RemoteException {
		libraries.remove(lib);
		System.out.println("lib remove from the libraries list");
	}




	@Override
	public IBook getBook(Long ISBN) throws RemoteException {
		System.out.println("get book");
		IBook book;
		for(ILibrary lib : libraries){
			book =  lib.getBook(ISBN);
			if(book!=null)
				return book;
		}
		return null;
	}

	@Override
	public IBook getBook(String title) throws RemoteException {
		IBook book;
		System.out.println("get book");
		for(ILibrary lib : libraries){
			book =  lib.getBook(title);
			if(book!=null)
				return book;
		}
		return null;
	}

	@Override
	public IBook removeBook(Long ISBN) throws RemoteException {
		IBook book;
		for(ILibrary lib : libraries){
			book =  lib.removeBook(ISBN);
			if(book!=null)
				return book;
		}
		return null;
	}

	@Override
	public List<IBook> getBooksByAuthor(String author) throws RemoteException {
		ArrayList<IBook> books = new ArrayList<IBook>();
		for(ILibrary lib : libraries){
			books.addAll(lib.getBooksByAuthor(author));
		}
		return books;
	}


	@Override
	public List<IBook> getAllBooks() throws RemoteException {
		ArrayList<IBook> books = new ArrayList<IBook>();
		for(ILibrary lib : libraries){
			books.addAll(lib.getAllBooks());
		}
		return books;
	}

	@Override
	public void addBook(IBook book) throws RemoteException {
		Integer min = libraries.get(0).NbLivre();
		ILibrary libMin= null;
		for (ILibrary iLibrary : libraries) {
			if(iLibrary.contains(book.getISBN())){
				iLibrary.addBook(book);
				return;
			}
			Integer Nblivre = iLibrary.NbLivre();
			if(Nblivre < min){
				min=Nblivre;
				libMin=iLibrary;
			}
			
		}
		libMin.addBook(book);
	}

	@Override
	public void addBook(Long isbn, String title, String author,Double price, Integer NbExemplaire) throws RemoteException{
		Integer min = libraries.get(0).NbLivre();
		ILibrary libMin= null;
		for (ILibrary iLibrary : libraries) {
			if(iLibrary.contains(isbn)){
				iLibrary.addBook(isbn, title, author, price, NbExemplaire);
				return;
			}
			Integer Nblivre = iLibrary.NbLivre();
			if(Nblivre <= min){
				min=Nblivre;
				libMin=iLibrary;
			}
		}
		libMin.addBook(isbn, title, author, price, NbExemplaire);
	}
	
	

	@Override
	public List<IBook> getBooksThatContain(String title) throws RemoteException {
		ArrayList<IBook> books = new ArrayList<IBook>();
		for(ILibrary lib : libraries){
			books.addAll(lib.getBooksThatContain(title));
		}
		return books;
	}
}
