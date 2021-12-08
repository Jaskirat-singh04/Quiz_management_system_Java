package quizCreator;

public class QuestionException extends Exception{

	public String toString(){
		return "Error: Invalid Question";
	}
}

class InvalidQuestionAnswer extends QuestionException{
	public String toString(){
		return "Error: Invalid option number";
	}
}

class QuestionAlreadyExist extends QuestionException{
	public String toString(){
		return "Error: Question already exists";
	}
}

class IdNotFound extends QuestionException{
	public String toString(){
		return "Error: Question ID does not exist";
	}
}

class InvalidId extends QuestionException{
	public String toString(){
		return "Error: Invalid id format(99 < id < 1000) ";
	}
}

class OptionAlreadyExist extends QuestionException{
	public String toString(){
		return "Error: Option already exists";
	}
} 

class IdAlreadyExist extends QuestionException{
	public String toString(){
		return "Error: ID already exists";
	}
} 

class QuizAlreadyExist extends QuestionException{
	public String toString(){
		return "Error: Quiz already exists! Enter a different quiz title";
	}
} 

class WrongInputFormat extends QuestionException{
	public String toString(){
		return "Error: Invalid Input Format (Usage of _ not allowed)" ;
	}
}

