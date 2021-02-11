public class Minecraft {
  private static final int N = 4;
  public static int[][] rotateFloorPlan(int[][] originalFloorPlan){
    if (originalFloorPlan != null){
      for (int i = 0; i < N / 2; i++){
        for (int j = i; j < N - i - 1; j++){
            int temp = originalFloorPlan[i][j];
            originalFloorPlan[i][j] = originalFloorPlan[N - 1 - j][i];
            originalFloorPlan[N - 1 - j][i] = originalFloorPlan[N - 1 - i][N - 1 - j];
            originalFloorPlan[N - 1 - i][N - 1 - j] = originalFloorPlan[j][N - 1 - i];
            originalFloorPlan[j][N - 1 - i] = temp;
        }
      }
      return originalFloorPlan;
    }
    return null;
  }
}
