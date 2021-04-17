package view.managedBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import persistence.dao.CommandeDao;
import persistence.dao.CommandeDaoImpl;
import persistence.dao.EtatDao;
import persistence.dao.EtatDaoImpl;
import persistence.entities.Commande;
import persistence.entities.Etat;
import persistence.entities.Produit;

@ManagedBean
@SessionScoped
public class CommandeMBean {

	private Commande commande = new Commande();
	private Commande selectedCommande = new Commande();
	CommandeDao commandeDao = new CommandeDaoImpl();
	EtatDao etatDao = new EtatDaoImpl();
	private List<Commande> listCommande = new ArrayList<Commande>();
	private List<Etat> listEtat = new ArrayList<Etat>();
	private BigDecimal valeurRecherche;
	private Date dateDebut;
	private Date dateFin;
	private PieChartModel pieModel;
	private BarChartModel barModel;
	ProduitMBean prod = new ProduitMBean();

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public CommandeMBean() {
		listCommande = commandeDao.findAll();
		createPieModel();
		createBarModel();
	}

	public List<Etat> getListEtat() {
		this.listEtat = etatDao.findAll();
		return listEtat;
	}

	public void setListEtat(List<Etat> listEtat) {
		this.listEtat = listEtat;
	}

	public BigDecimal getValeurRecherche() {
		return valeurRecherche;
	}

	public void setValeurRecherche(BigDecimal valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public void setListCommande(List<Commande> listCommande) {
		this.listCommande = listCommande;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setClient(Commande commande) {
		this.commande = commande;
	}

	public Commande getSelectedCommande() {
		return selectedCommande;
	}

	public void setSelectedCommande(Commande selectedCommande) {
		this.selectedCommande = selectedCommande;
	}

	public List<Commande> getListCommande() {
		return listCommande;
	}

	public void setListClient(List<Commande> listCommande) {
		this.listCommande = listCommande;
	}

	public void addCommande(ActionEvent e) {
		Etat etat = new Etat();
		etat.setIdetat(new BigDecimal(1));
		commande.setEtat(etat);
		commandeDao.add(commande);
		commande = new Commande();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ajout effectué avec succès"));
	}

	public void deleteCommande(ActionEvent e) {
		if (selectedCommande == null || selectedCommande.getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Aucun  commande sélectionné"));
		} else {
			commandeDao.delete(selectedCommande);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Suppression effectué avec succès"));
		}
	}

	public String editCommande() {
		if (selectedCommande == null || selectedCommande.getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attention", "Aucun  commande sélectionné"));
			return "showCommande.xhtml";
		} else {
			return "editCommande.xhtml";
		}

	}

	public void updateCommande(ActionEvent e) {
		commandeDao.update(selectedCommande);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Modification effectué avec succès"));
	}

	public void findByEtat(ActionEvent e) {
		this.listCommande = commandeDao.findByEtat(valeurRecherche);
	}

	public void findByDate(ActionEvent e) {
		System.out.println(dateDebut);
		this.listCommande = commandeDao.findByDate(dateDebut, dateFin);
	}

	private void createPieModel() {
		pieModel = new PieChartModel();
		ChartData data = new ChartData();

		PieChartDataSet dataSet = new PieChartDataSet();
		List<Number> values = new ArrayList<>();
		values.add(commandeDao.findByEtat(new BigDecimal(0)).size());
		values.add(commandeDao.findByEtat(new BigDecimal(1)).size());
		values.add(commandeDao.findByEtat(new BigDecimal(2)).size());
		dataSet.setData(values);

		List<String> bgColors = new ArrayList<>();
		bgColors.add("rgb(255, 99, 132)");
		bgColors.add("rgb(54, 162, 235)");
		bgColors.add("rgb(255, 205, 86)");
		dataSet.setBackgroundColor(bgColors);

		data.addChartDataSet(dataSet);
		List<String> labels = new ArrayList<>();
		labels.add("Annuler");
		labels.add("En cours");
		labels.add("Livré");
		data.setLabels(labels);

		pieModel.setData(data);
	}

	public void createBarModel() {
		barModel = new BarChartModel();
		ChartData data = new ChartData();

		BarChartDataSet barDataSet = new BarChartDataSet();
		barDataSet.setLabel("Nombre de Commande par produits");

		List<Number> values = new ArrayList<>();
		List<String> bgColor = new ArrayList<>();
		List<String> borderColor = new ArrayList<>();
		List<String> labels = new ArrayList<>();
		int i = 0;
		for (Produit p : prod.getListProduit()) {
			values.add(commandeDao.findByProduit(p).size());
			bgColor.add("rgba(255, 9" + i + ", 132, 0.2)");
			borderColor.add("rgb(255, 9" + i + ", 132)");
			labels.add(p.getMarqueproduit());
			i += 20;
		}
		barDataSet.setData(values);
		barDataSet.setBackgroundColor(bgColor);
		barDataSet.setBorderColor(borderColor);
		barDataSet.setBorderWidth(1);

		data.addChartDataSet(barDataSet);
		data.setLabels(labels);
		barModel.setData(data);

		// Options
		BarChartOptions options = new BarChartOptions();
		CartesianScales cScales = new CartesianScales();
		CartesianLinearAxes linearAxes = new CartesianLinearAxes();
		linearAxes.setOffset(true);
		CartesianLinearTicks ticks = new CartesianLinearTicks();
		ticks.setBeginAtZero(true);
		linearAxes.setTicks(ticks);
		cScales.addYAxesData(linearAxes);
		options.setScales(cScales);

		Legend legend = new Legend();
		legend.setDisplay(true);
		legend.setPosition("top");
		LegendLabel legendLabels = new LegendLabel();
		legendLabels.setFontStyle("bold");
		legendLabels.setFontColor("#2980B9");
		legendLabels.setFontSize(24);
		legend.setLabels(legendLabels);
		options.setLegend(legend);

		barModel.setOptions(options);
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}
}
