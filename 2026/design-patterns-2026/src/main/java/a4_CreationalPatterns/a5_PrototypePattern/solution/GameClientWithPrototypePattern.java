package a4_CreationalPatterns.a5_PrototypePattern.solution;

public class GameClientWithPrototypePattern {
  public static void main(String[] args) {

    GameBoard gameBoard = new GameBoard();
    gameBoard.addPiece(new GamePiece("Red", 1));
    gameBoard.addPiece(new GamePiece("Blue", 5));
    gameBoard.showBoardState();

    // CheckPoint state:
    GameBoard copiedBoard = gameBoard.clone();

    System.out.println("Copy Game Board State:");
    copiedBoard.showBoardState();
  }
}
