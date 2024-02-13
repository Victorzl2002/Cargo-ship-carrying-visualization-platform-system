package com.ruoyi.cargoinfo.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import com.ruoyi.allocation.domain.BAllocation;
import com.ruoyi.allocation.mapper.BAllocationMapper;
import com.ruoyi.cargoinfo.domain.GeneticAlgorithmResult;
import com.ruoyi.container.domain.BContainer;
import com.ruoyi.container.mapper.BContainerMapper;
import com.ruoyi.order.domain.BOrder;
import com.ruoyi.order.mapper.BOrderMapper;
import com.ruoyi.spacenote.domain.BSpacenote;
import com.ruoyi.spacenote.mapper.BSpacenoteMapper;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cargoinfo.mapper.BCargoMapper;
import com.ruoyi.cargoinfo.domain.BCargo;
import com.ruoyi.cargoinfo.service.IBCargoService;


/**
 * 货物管理Service业务层处理
 * 
 * @author victorzl
 * @date 2023-12-15
 */
@Service
public class BCargoServiceImpl implements IBCargoService 
{
    @Autowired
    private BCargoMapper bCargoMapper;

    @Autowired
    private BContainerMapper bContainerMapper;

    @Autowired
    private BSpacenoteMapper bSpacenoteMapper;

    @Autowired
    private BAllocationMapper bAllocationMapper;

    @Autowired
    private BOrderMapper bOrderMapper;
    /**
     * 查询货物管理
     * 
     * @param cargoId 货物管理主键
     * @return 货物管理
     */
    @Override
    public BCargo selectBCargoByCargoId(Long cargoId)
    {
        return bCargoMapper.selectBCargoByCargoId(cargoId);
    }

    /**
     * 查询货物管理列表
     * 
     * @param bCargo 货物管理
     * @return 货物管理
     */
    @Override
    public List<BCargo> selectBCargoList(BCargo bCargo)
    {
        return bCargoMapper.selectBCargoList(bCargo);
    }

    /**
     * 新增货物管理
     * 
     * @param bCargo 货物管理
     * @return 结果
     */
    @Override
    public int insertBCargo(BCargo bCargo)
    {
        return bCargoMapper.insertBCargo(bCargo);
    }

    /**
     * 修改货物管理
     * 
     * @param bCargo 货物管理
     * @return 结果
     */
    @Override
    public int updateBCargo(BCargo bCargo)
    {
        return bCargoMapper.updateBCargo(bCargo);
    }

    /**
     * 批量删除货物管理
     * 
     * @param cargoIds 需要删除的货物管理主键
     * @return 结果
     */
    @Override
    public int deleteBCargoByCargoIds(Long[] cargoIds)
    {
        return bCargoMapper.deleteBCargoByCargoIds(cargoIds);
    }

    /**
     * 删除货物管理信息
     * 
     * @param cargoId 货物管理主键
     * @return 结果
     */
    @Override
    public int deleteBCargoByCargoId(Long cargoId)
    {
        return bCargoMapper.deleteBCargoByCargoId(cargoId);
    }

    // 实现具体的货物分配逻辑
    @Override
    public void allocateCargoForShippingPlan(String shippingPlanId, List<BOrder> orders) {
        System.out.println("Allocating cargo for Shipping Plan ID: " + shippingPlanId);
        List<BCargo> bCargoList=new ArrayList<>();
        for (BOrder order : orders) {
           bCargoList.add(bCargoMapper.selectBCargoByCargoId(order.getCargoId()));
        }
        List<BCargo> ganhuo=new ArrayList<>();
        List<BCargo> lengcang=new ArrayList<>();
        List<BCargo> kaiding=new ArrayList<>();
        BContainer bContainer=new BContainer();
        BContainer bContainer1=new BContainer();
        BContainer bContainer2=new BContainer();
        bContainer.setContainerType("干货集装箱");
        bContainer.setContainerLocation("连云港");
        bContainer.setContainerStatus("未装载");
        bContainer1.setContainerType("冷藏集装箱");
        bContainer1.setContainerLocation("连云港");
        bContainer1.setContainerStatus("未装载");
        bContainer2.setContainerType("开顶集装箱");
        bContainer2.setContainerLocation("连云港");
        bContainer2.setContainerStatus("未装载");
        List<BContainer> bContainerList=bContainerMapper.selectBContainerList(bContainer);
        List<BContainer> bContainerList1=bContainerMapper.selectBContainerList(bContainer1);
        List<BContainer> bContainerList2=bContainerMapper.selectBContainerList(bContainer2);
        bCargoList.forEach(s->{
            if (s!=null&&s.getCargoStatus().equals("未分配"))
            {
                if (s.getCargoType().contains("电器")||s.getCargoType().contains("家电"))
                {
                    ganhuo.add(s);
                }
                if (s.getCargoType().contains("冷冻冷藏"))
                {
                    lengcang.add(s);
                }
                if (s.getCargoType().contains("钢铁"))
                {
                    kaiding.add(s);
                }
            }
        });
        if (ganhuo.size()>0)
        {
            allocatecargotype(shippingPlanId,bContainerList,ganhuo,orders);
        }
        if (lengcang.size()>0)
        {
            allocatecargotype(shippingPlanId,bContainerList1,lengcang,orders);
        }
        if (lengcang.size()>0)
        {
            allocatecargotype(shippingPlanId,bContainerList2,kaiding,orders);
        }
    }

    @Override
    public void allocatecargotype(String shippingPlanId,List<BContainer> bContainerList, List<BCargo> huowu,List<BOrder> orders) {
        AtomicBoolean conditionMet = new AtomicBoolean(true);
        for (int i = 0; i < bContainerList.size(); i++) {
            conditionMet.set(false);
            Iterator<BCargo> iterator = huowu.iterator();
            while (iterator.hasNext()) {
                BCargo s = iterator.next();
                System.out.println(s);
                if (s.getCargoStatus().equals("未分配")) {
                    conditionMet.set(true);
                }

                if (s.getCargoStatus().equals("已分配")) {
                    iterator.remove(); // 使用迭代器的 remove 方法安全地移除元素
                }
            }
            if (conditionMet.get()==false)
            {
                break;
            }
            int[] weights= new int[huowu.size()];
            int[] volumes= new int[huowu.size()];
            int[] cargoid= new  int[huowu.size()];
            for (int x = 0; x < huowu.size(); x++) {
                BCargo cargo = huowu.get(x);
                weights[x]=Double.valueOf(cargo.getCargoWeight()).intValue();
                volumes[x] = Double.valueOf(cargo.getCargoVolume()).intValue();;
                cargoid[x]= Math.toIntExact(cargo.getCargoId());
            }
            int maxWeight = Double.valueOf(bContainerList.get(i).getMaxWeight()).intValue();
            int maxVolume = Double.valueOf(bContainerList.get(i).getMaxVolume()).intValue();
            int populationSize = 100;
            int generations = 100;
            GeneticAlgorithmResult result = runGeneticAlgorithm(weights, volumes, maxWeight, maxVolume, populationSize, generations);
            System.out.println("最佳解决方案：" + java.util.Arrays.toString(result.bestIndividual));
            System.out.println("最大集装箱空间利用率：" + result.utilization);
            // 输出货物分配方案
            System.out.println("货物分配方案：");
            printAllocation(result.bestIndividual, cargoid, weights, volumes,huowu,bContainerList.get(i),result.utilization,shippingPlanId,orders);
        }
    }

    //遗传算法分配货物
    public GeneticAlgorithmResult runGeneticAlgorithm(int[] weights, int[] volumes, int maxWeight, int maxVolume, int populationSize, int generations) {
        List<int[]> population = initPopulation(populationSize, weights.length);

        for (int i = 0; i < generations; i++) {
            population = selection(population, weights, volumes, maxWeight, maxVolume);
            List<int[]> newPopulation = new ArrayList<>();
            while (newPopulation.size() < populationSize) {
                int[] parent1 = getRandomIndividual(population);
                int[] parent2 = getRandomIndividual(population);
                int[] child1 = crossover(parent1, parent2);
                int[] child2 = crossover(parent2, parent1);
                mutation(child1);
                mutation(child2);
                newPopulation.add(child1);
                newPopulation.add(child2);
            }
            population = newPopulation;
        }
        int[] bestIndividual = getBestIndividual(population, weights, volumes, maxWeight, maxVolume);
        double utilization = calculateUtilization(bestIndividual, volumes, maxVolume);
        return new GeneticAlgorithmResult(bestIndividual, utilization);
    }

    private  List<int[]> initPopulation(int populationSize, int numItems) {
        List<int[]> population = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < populationSize; i++) {
            int[] individual = new int[numItems];
            for (int j = 0; j < numItems; j++) {
                individual[j] = random.nextInt(2);
            }
            population.add(individual);
        }
        return population;
    }

    private  double calculateUtilization(int[] individual, int[] volumes, int totalVolume) {
        int usedVolume = 0;
        for (int i = 0; i < individual.length; i++) {
            usedVolume += individual[i] * volumes[i];
        }
        return (double) usedVolume / totalVolume;
    }

    private  List<int[]> selection(List<int[]> population, int[] weights, int[] volumes, int maxWeight, int maxVolume) {
        List<int[]> selectedPopulation = new ArrayList<>();
        while (selectedPopulation.size() < population.size()) {
            int[] parent1 = rouletteWheelSelection(population, weights, volumes, maxWeight, maxVolume);
            int[] parent2 = rouletteWheelSelection(population, weights, volumes, maxWeight, maxVolume);
            int[] child1 = crossover(parent1, parent2);
            int[] child2 = crossover(parent2, parent1);
            mutation(child1);
            mutation(child2);
            selectedPopulation.add(child1);
            selectedPopulation.add(child2);
        }
        return selectedPopulation;
    }

    private  int[] rouletteWheelSelection(List<int[]> population, int[] weights, int[] volumes, int maxWeight, int maxVolume) {
        double totalFitness = 0;
        double[] fitnessValues = new double[population.size()];
        for (int i = 0; i < population.size(); i++) {
            fitnessValues[i] = calculateUtilization(population.get(i), volumes, maxVolume);
            totalFitness += fitnessValues[i];
        }
        double threshold = Math.random() * totalFitness;
        double sum = 0;
        for (int i = 0; i < population.size(); i++) {
            sum += fitnessValues[i];
            if (sum >= threshold) {
                return population.get(i);
            }
        }
        return population.get(population.size() - 1);
    }

    private  int[] crossover(int[] parent1, int[] parent2) {
//        Random random = new Random();
//        int crossoverPoint = random.nextInt(parent1.length - 1) + 1;
//        int[] child = new int[parent1.length];
//        System.arraycopy(parent1, 0, child, 0, crossoverPoint);
//        System.arraycopy(parent2, crossoverPoint, child, crossoverPoint, parent2.length - crossoverPoint);
//        return child;
        Random random = new Random();
        int length = parent1.length;

        if (length == 1) {
            // 特殊情况：当只有一个物品时，随机选择 parent1 或 parent2 作为子代
            return random.nextBoolean() ? parent1.clone() : parent2.clone();
        }

        int crossoverPoint = random.nextInt(length - 1) + 1;
        int[] child = new int[length];
        System.arraycopy(parent1, 0, child, 0, crossoverPoint);
        System.arraycopy(parent2, crossoverPoint, child, crossoverPoint, length - crossoverPoint);
        return child;
    }

    private  void mutation(int[] individual) {
        Random random = new Random();
        double mutationRate = 0.1; // Adjust the mutation rate as needed
        for (int i = 0; i < individual.length; i++) {
            if (Math.random() < mutationRate) {
                individual[i] = 1 - individual[i];
            }
        }
    }

    private  int[] getBestIndividual(List<int[]> population, int[] weights, int[] volumes, int maxWeight, int maxVolume) {
        double maxUtilization = 0;
        int[] bestIndividual = new int[weights.length];
        for (int[] individual : population) {
            double utilization = calculateUtilization(individual, volumes, maxVolume);
            if (utilization > maxUtilization && isValid(individual, weights, volumes, maxWeight, maxVolume)) {
                maxUtilization = utilization;
                bestIndividual = individual.clone();
            }
        }
        return bestIndividual;
    }

    private  boolean isValid(int[] individual, int[] weights, int[] volumes, int maxWeight, int maxVolume) {
        int totalWeight = 0;
        int totalVolume = 0;
        for (int i = 0; i < individual.length; i++) {
            totalWeight += individual[i] * weights[i];
            totalVolume += individual[i] * volumes[i];
        }
        return totalWeight <= maxWeight && totalVolume <= maxVolume;
    }

    private  int[] getRandomIndividual(List<int[]> population) {
        Random random = new Random();
        int index = random.nextInt(population.size());
        return population.get(index);
    }

    private void printAllocation(int[] allocation, int[] cargoid, int[] weights, int[] volumes,List<BCargo> ganhuo,BContainer container,double rate,String shippingPlanId,List<BOrder> orders) {
        System.out.println("选中的货物：");
        boolean istrue=false;
        Date date=new Date();
        for (int i = 0; i < allocation.length; i++) {
            if (allocation[i] == 1) {
                istrue=true;
                System.out.println("货物" + cargoid[i] + ": 重量=" + weights[i] + ", 体积=" + volumes[i]);
                ganhuo.get(i).setCargoStatus("已分配");
                BOrder bOrder=bOrderMapper.selectBOrderByCargoId(Long.valueOf(cargoid[i]));
                bOrder.setOrderStatus("订单已分配");
                bOrderMapper.updateBOrder(bOrder);
                BCargo cargo=bCargoMapper.selectBCargoByCargoId(Long.valueOf(cargoid[i]));
                cargo.setCargoStatus("已分配");
                bCargoMapper.updateBCargo(cargo);
                container.setCurrentWeight(container.getCurrentWeight()+cargo.getCargoWeight());
                container.setCurrentVolume(container.getCurrentVolume()+cargo.getCargoVolume());
                bContainerMapper.updateBContainer(container);
                BAllocation bAllocation=new BAllocation();
                bAllocation.setAllocationDate(date);
                bAllocation.setCargoId(Long.valueOf(cargoid[i]));
                bAllocation.setContainerId(container.getContainerId());
                bAllocation.setScheduleId(Long.valueOf(shippingPlanId));
                bAllocationMapper.insertBAllocation(bAllocation);
            }
        }
        if (istrue==true)
        {
            container.setContainerStatus("已装载");
            bContainerMapper.updateBContainer(container);
            BSpacenote spacenote=new BSpacenote();
            spacenote.setContainerId(container.getContainerId());
            spacenote.setRate(String.valueOf(rate));
            spacenote.setCreatedate(date);
            spacenote.setScheduleId(Long.valueOf(shippingPlanId));
            bSpacenoteMapper.insertBSpacenote(spacenote);
        }
    }

}
