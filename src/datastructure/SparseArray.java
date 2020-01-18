package datastructure;


/**
 * 以棋盘为背景，介绍稀疏数组与原始数组之间的相互转换
 * Created by qjq on 2020/1/18 15:25
 */
public class SparseArray {
    /**
     * 将原始二维数组转换为稀疏数组
     * @param OriArr
     */
    public static void OriArr2SparseArr(int[][] OriArr){
        //输出原始数组
        System.out.println("原始数组为：");
        for (int i = 0; i < OriArr.length; i++) {
            for (int j = 0; j < OriArr[i].length; j++) {
                System.out.printf("%d\t",OriArr[i][j]);
            }
            System.out.println();
        }

        //将二维数组转换为稀疏数组
        //1.遍历原始二维数组，求出一共多少非零元素
        int sum=0;
        for (int i = 0; i < OriArr.length; i++) {
            for (int j = 0; j < OriArr[i].length; j++) {
                if(OriArr[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println("非零元素的个数："+sum);
        //2.创建稀疏数组
        int count = 0;//计数：非零元素
        int[][] sparseArr = new int[sum+1][3];
        sparseArr[0][0]=OriArr.length;//行数
        sparseArr[0][1]=OriArr[0].length;//列数
        sparseArr[0][2]=sum;
        //3.将二维数组的有效数据存放进稀疏数组
        for (int i = 0; i < OriArr.length; i++) {
            for (int j = 0; j < OriArr[i].length; j++) {
                if(OriArr[i][j]!=0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=OriArr[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println("稀疏数组为：");
        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < sparseArr[i].length; j++) {
                System.out.printf("%d\t",sparseArr[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 将稀疏数组转换为二维数组
     * @param sparseArr
     */
    public static void SparseArr2OriArr(int[][] sparseArr){
        //输出稀疏数组
        System.out.println("稀疏数组为：");
        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < sparseArr[i].length; j++) {
                System.out.printf("%d\t",sparseArr[i][j]);
            }
            System.out.println();
        }
        //1.先读取稀疏数组的第一行数据，根据第一行数据，创建原始二维数组
        int[][] OriArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.读取稀疏数组后几行的数据，并赋值给原始二维数组
        for (int i = 1; i < sparseArr[0][2]+1; i++) {
            OriArr[sparseArr[i][0]][sparseArr[i][0]]=sparseArr[i][2];
        }
        //输出原始二维数组
        System.out.println("原始二维数组为：");
        for (int i = 0; i < OriArr.length; i++) {
            for (int j = 0; j < OriArr[i].length; j++) {
                System.out.printf("%d\t",OriArr[i][j]);
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        //原始数组
        //1表示黑子，2表示白子
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        OriArr2SparseArr(chessArr);

        //稀疏数组
        int[][] sparseArr = {
                {11,11,2},
                {1,2,1},
                {2,3,2}
        };
        SparseArr2OriArr(sparseArr);
    }
}
