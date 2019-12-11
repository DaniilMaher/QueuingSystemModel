package com.maher.queingsystem.com.maher.queingsystem.runner;

import com.maher.queingsystem.object.Operator;
import com.maher.queingsystem.object.Queue;
import com.maher.queingsystem.object.Source;
import com.maher.queingsystem.object.SystemObject;
import com.maher.queingsystem.system.QueueingSystem;
import java.util.Scanner;

public class Runner {

    private static int sourcePeriod;
    private static int queueCapacity = 2;
    private static double firstOperatorParameter ;
    private static double secondOperatorParameter;
    private static int tactsCount =  1_000_000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите период источника: ");
        sourcePeriod = scanner.nextInt();
        System.out.print("Введите вероятность просеивания первого кана-ла: ");
        firstOperatorParameter = scanner.nextDouble();
        System.out.print("Введите вероятность просеивания второго кана-ла: ");
        secondOperatorParameter = scanner.nextDouble();
        QueueingSystem system = prepareSystem();
        system.doModeling();
        System.out.println("\nПроизведено моделирование в " + tactsCount + " единиц времени. Рассчитаны параметры: ");
        System.out.println("Абсолютная пропускная способность: " + system.calculateAbsoluteBandwidth());
        System.out.println("Средняя длина очереди: " + system.calculateAverageQueue());
        System.out.println("Вероятность отказа: " + system.calculateRejectProbability());
    }

    private static QueueingSystem prepareSystem() {
        SystemObject object = new Source(sourcePeriod);
        object.setNext(new Queue(queueCapacity));
        object = object.getNext();
        object.setNext(new Operator(firstOperatorParameter));
        object = object.getNext();
        object.setNext(new Operator(secondOperatorParameter));
        object = object.getNext();
        return new QueueingSystem(tactsCount, object);
    }
}
