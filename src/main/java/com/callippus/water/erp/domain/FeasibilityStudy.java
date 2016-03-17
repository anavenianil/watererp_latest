package com.callippus.water.erp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FeasibilityStudy.
 */
@Entity
@Table(name = "feasibility_study")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FeasibilityStudy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "plot_area_in_sq_mtrs")
    private Float plotAreaInSqMtrs;
    
    @Column(name = "plot_area_in_yards")
    private Float plotAreaInYards;
    
    @Column(name = "no_of_flats_or_no_of_units")
    private Integer noOfFlatsOrNoOfUnits;
    
    @Column(name = "no_of_floors")
    private Integer noOfFloors;
    
    @Column(name = "total_plinth_area")
    private Float totalPlinthArea;
    
    @Column(name = "water_requirement")
    private Float waterRequirement;
    
    @ManyToOne
    @JoinColumn(name = "scheme_master_id")
    private SchemeMaster schemeMaster;

    @ManyToOne
    @JoinColumn(name = "tariff_category_master_id")
    private TariffCategoryMaster tariffCategoryMaster;

    @ManyToOne
    @JoinColumn(name = "make_of_water_pipe_id")
    private MakeOfPipe makeOfWaterPipe;

    @ManyToOne
    @JoinColumn(name = "make_of_sewerage_pipe_id")
    private MakeOfPipe makeOfSeweragePipe;

    @ManyToOne
    @JoinColumn(name = "main_water_size_id")
    private MainWaterSize mainWaterSize;

    @ManyToOne
    @JoinColumn(name = "main_sewerage_size_id")
    private MainSewerageSize mainSewerageSize;

    @ManyToOne
    @JoinColumn(name = "docket_code_id")
    private DocketCode docketCode;

    @ManyToOne
    @JoinColumn(name = "application_txn_id")
    private ApplicationTxn applicationTxn;

    @ManyToOne
    @JoinColumn(name = "category_master_id")
    private CategoryMaster categoryMaster;

    @ManyToOne
    @JoinColumn(name = "sewer_size_id")
    private SewerSize sewerSize;

    @ManyToOne
    @JoinColumn(name = "pipe_size_master_id")
    private PipeSizeMaster pipeSizeMaster;

    @ManyToOne
    @JoinColumn(name = "feasibility_status_id")
    private FeasibilityStatus feasibilityStatus;

    @OneToMany(mappedBy = "feasibilityStudy", cascade=CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<ItemRequired> itemRequired = new ArrayList<>();
    
    public List<ItemRequired> getItemRequired() {
		return itemRequired;
	}

	public void setItemRequired(List<ItemRequired> itemRequired) {
		this.itemRequired = itemRequired;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPlotAreaInSqMtrs() {
        return plotAreaInSqMtrs;
    }
    
    public void setPlotAreaInSqMtrs(Float plotAreaInSqMtrs) {
        this.plotAreaInSqMtrs = plotAreaInSqMtrs;
    }

    public Float getPlotAreaInYards() {
        return plotAreaInYards;
    }
    
    public void setPlotAreaInYards(Float plotAreaInYards) {
        this.plotAreaInYards = plotAreaInYards;
    }

    public Integer getNoOfFlatsOrNoOfUnits() {
        return noOfFlatsOrNoOfUnits;
    }
    
    public void setNoOfFlatsOrNoOfUnits(Integer noOfFlatsOrNoOfUnits) {
        this.noOfFlatsOrNoOfUnits = noOfFlatsOrNoOfUnits;
    }

    public Integer getNoOfFloors() {
        return noOfFloors;
    }
    
    public void setNoOfFloors(Integer noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public Float getTotalPlinthArea() {
        return totalPlinthArea;
    }
    
    public void setTotalPlinthArea(Float totalPlinthArea) {
        this.totalPlinthArea = totalPlinthArea;
    }

    public Float getWaterRequirement() {
        return waterRequirement;
    }
    
    public void setWaterRequirement(Float waterRequirement) {
        this.waterRequirement = waterRequirement;
    }

    public SchemeMaster getSchemeMaster() {
        return schemeMaster;
    }

    public void setSchemeMaster(SchemeMaster schemeMaster) {
        this.schemeMaster = schemeMaster;
    }

    public TariffCategoryMaster getTariffCategoryMaster() {
        return tariffCategoryMaster;
    }

    public void setTariffCategoryMaster(TariffCategoryMaster tariffCategoryMaster) {
        this.tariffCategoryMaster = tariffCategoryMaster;
    }

    public MakeOfPipe getMakeOfWaterPipe() {
        return makeOfWaterPipe;
    }

    public void setMakeOfWaterPipe(MakeOfPipe makeOfPipe) {
        this.makeOfWaterPipe = makeOfPipe;
    }

    public MakeOfPipe getMakeOfSeweragePipe() {
        return makeOfSeweragePipe;
    }

    public void setMakeOfSeweragePipe(MakeOfPipe makeOfPipe) {
        this.makeOfSeweragePipe = makeOfPipe;
    }

    public MainWaterSize getMainWaterSize() {
        return mainWaterSize;
    }

    public void setMainWaterSize(MainWaterSize mainWaterSize) {
        this.mainWaterSize = mainWaterSize;
    }

    public MainSewerageSize getMainSewerageSize() {
        return mainSewerageSize;
    }

    public void setMainSewerageSize(MainSewerageSize mainSewerageSize) {
        this.mainSewerageSize = mainSewerageSize;
    }

    public DocketCode getDocketCode() {
        return docketCode;
    }

    public void setDocketCode(DocketCode docketCode) {
        this.docketCode = docketCode;
    }

    public ApplicationTxn getApplicationTxn() {
        return applicationTxn;
    }

    public void setApplicationTxn(ApplicationTxn applicationTxn) {
        this.applicationTxn = applicationTxn;
    }

    public CategoryMaster getCategoryMaster() {
        return categoryMaster;
    }

    public void setCategoryMaster(CategoryMaster categoryMaster) {
        this.categoryMaster = categoryMaster;
    }

    public SewerSize getSewerSize() {
        return sewerSize;
    }

    public void setSewerSize(SewerSize sewerSize) {
        this.sewerSize = sewerSize;
    }

    public PipeSizeMaster getPipeSizeMaster() {
        return pipeSizeMaster;
    }

    public void setPipeSizeMaster(PipeSizeMaster pipeSizeMaster) {
        this.pipeSizeMaster = pipeSizeMaster;
    }

    public FeasibilityStatus getFeasibilityStatus() {
        return feasibilityStatus;
    }

    public void setFeasibilityStatus(FeasibilityStatus feasibilityStatus) {
        this.feasibilityStatus = feasibilityStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FeasibilityStudy feasibilityStudy = (FeasibilityStudy) o;
        if(feasibilityStudy.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, feasibilityStudy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FeasibilityStudy{" +
            "id=" + id +
            ", plotAreaInSqMtrs='" + plotAreaInSqMtrs + "'" +
            ", plotAreaInYards='" + plotAreaInYards + "'" +
            ", noOfFlatsOrNoOfUnits='" + noOfFlatsOrNoOfUnits + "'" +
            ", noOfFloors='" + noOfFloors + "'" +
            ", totalPlinthArea='" + totalPlinthArea + "'" +
            ", waterRequirement='" + waterRequirement + "'" +
            '}';
    }
}
