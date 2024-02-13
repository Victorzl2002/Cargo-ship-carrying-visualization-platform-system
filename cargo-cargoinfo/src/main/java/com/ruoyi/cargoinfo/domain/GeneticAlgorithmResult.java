package com.ruoyi.cargoinfo.domain;

/**
 * @BelongsProject: Cargo-ship-carrying-visualization-platform-system
 * @BelongsPackage: com.ruoyi.cargoinfo.domain
 * @Author: VictorZl
 * @CreateTime: 2024-01-20  11:02
 * @Description: TODO
 * @Version: 1.0
 */
public class GeneticAlgorithmResult {
    public int[] bestIndividual;
    public double utilization;

    public GeneticAlgorithmResult(int[] bestIndividual, double utilization) {
        this.bestIndividual = bestIndividual;
        this.utilization = utilization;
    }
}
