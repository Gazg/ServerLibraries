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
		if(index >=libraries.size()){
			index =0;
		}
		libraries.get(index).addBook(book);
		index++;
	}

	@Override
	public void addBook(Long isbn, String title, String author,Double price) throws RemoteException{
		if(index >=libraries.size()){
			index =0;
		}
		libraries.get(index).addBook(isbn,title,author,price);
		index++;
	}

	@Override
	public List<IBook> getBooksThatContain(String title) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


}
