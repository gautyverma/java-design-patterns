package a4_CreationalPatterns.a5_PrototypePattern.problem;

public class GameClientWithoutPrototypePattern {
  public static void main(String[] args) {

    GameBoard gameBoard = new GameBoard();
    gameBoard.addPiece(new GamePiece("Red", 1));
    gameBoard.addPiece(new GamePiece("Blue", 5));
    gameBoard.showBoardState();

    // CheckPoint state:
    GameBoard copiedBoard = new GameBoard();
    for (GamePiece piece : gameBoard.getPieces()) {
      copiedBoard.addPiece(new GamePiece(piece.getColor(), piece.getPosition()));
    }
    System.out.println("Copy Game Board State:");
    copiedBoard.showBoardState();
  }
}
