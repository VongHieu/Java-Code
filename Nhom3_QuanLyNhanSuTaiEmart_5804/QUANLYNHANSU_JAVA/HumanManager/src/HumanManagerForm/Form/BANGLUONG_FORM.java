/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HumanManagerForm.Form;

import HumanManagerForm.Connection.DatabaseConnection;
import HumanManagerForm.Dao.BangLuongDAO;
import HumanManagerForm.Dao.ChamCongDAO;
import HumanManagerForm.Model.BangLuong;
import HumanManagerForm.Model.ChamCong;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author vongh
 */
public class BANGLUONG_FORM extends javax.swing.JFrame {

    DefaultTableModel tableModel;
    private int duoi = 0;
    private int tren = 0;

    /**
     * Creates new form BANGLUONG_FORM
     */
    public BANGLUONG_FORM() {
        initComponents();
        setLocationRelativeTo(null);
        initCombobox();
        DesignTalbe();
        loadDulieu();
        LoadIcon();
    }

    public void LoadIcon() {
        Image icon = Toolkit.getDefaultToolkit().getImage("\\QUANLYNHANSU_JAVA\\HumanManager\\src\\HumanManagerForm\\Icon\\emart-logo.jpg");
        this.setIconImage(icon);
    }

    private void initCombobox() {
        try {
            String sql = "Select BANGCHAMCONG.MANV from NHANVIEN,BANGCHAMCONG WHERE NHANVIEN.MANV=BANGCHAMCONG.MANV";
            Connection conn = DatabaseConnection.openConnection();
            PreparedStatement pm = conn.prepareStatement(sql);
            ResultSet rs = pm.executeQuery();
            cbbmanv.removeAllItems();
            while (rs.next()) {
                cbbmanv.addItem(rs.getString("MANV"));
            }
            rs.close();
            pm.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void DesignTalbe() {
        tblluong.getTableHeader().setFont(new Font("Quicksand light", Font.BOLD, 14));
        tblluong.getTableHeader().setOpaque(false);
        tblluong.getTableHeader().setBackground(new Color(32, 136, 203));
        tblluong.setRowHeight(25);

    }

    private void SuKien() {
        txtmabl.setText(tblluong.getValueAt(duoi - 1, 0).toString());
        cbbmanv.setSelectedItem(tblluong.getValueAt(duoi - 1, 1).toString());
        txttennv.setText(tblluong.getValueAt(duoi - 1, 2).toString());
        txtchuvu.setText(tblluong.getValueAt(duoi - 1, 3).toString());
        txtthang.setText(tblluong.getValueAt(duoi - 1, 4).toString());
        txtnam.setText(tblluong.getValueAt(duoi - 1, 5).toString());
        txtluongcb.setText(tblluong.getValueAt(duoi - 1, 6).toString());
        txtngaycong.setText(tblluong.getValueAt(duoi - 1, 7).toString());
        txtphucap.setText(tblluong.getValueAt(duoi - 1, 8).toString());
        txtbaohiem.setText(tblluong.getValueAt(duoi - 1, 9).toString());
        txttienthue.setText(tblluong.getValueAt(duoi - 1, 10).toString());
        txttamung.setText(tblluong.getValueAt(duoi - 1, 11).toString());
        txtluongthuc.setText(tblluong.getValueAt(duoi - 1, 12).toString());
        txtngaynhan.setText(tblluong.getValueAt(duoi - 1, 13).toString());
    }
    ArrayList<BangLuong> A = new ArrayList<BangLuong>();

    private void loadDulieu() {
        A.clear();
        try {
            tblluong.removeAll();
            String[] arr = {"Mã BL", "Mã NV", "Tên NV", "Chức vụ", "Tháng", "Năm", "Lương CB", "Ngày công", "Tổng PC", "Bảo hiểm", "Thuế", "Tạm ứng", "Lương thực nhận", "ngày nhận"};
            tableModel = new DefaultTableModel(arr, 0);
            String sql = "select * from BANGLUONG";
            Connection conn = DatabaseConnection.openConnection();
            PreparedStatement pm = conn.prepareStatement(sql);
            ResultSet rs = pm.executeQuery();
            tableModel.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                    rs.getString("MABL"), rs.getString("MANV"), rs.getString("TenNV"), rs.getString("TENCV"), rs.getString("THANG"), rs.getString("NAM"), rs.getString("MucluongCB"), rs.getString("SoNgayCong"), rs.getString("TongPC"),
                    rs.getString("TienBH"), rs.getString("SoTienThue"), rs.getString("TamUng"), rs.getString("LuongTH"), rs.getString("NgayNhan")};
                tableModel.addRow(row);
                BangLuong bl = new BangLuong(rs.getString("MABL"), rs.getString("MANV"), rs.getString("TenNV"), rs.getString("TENCV"), rs.getString("NgayNhan"), Integer.valueOf(rs.getString("THANG")), Integer.valueOf(rs.getString("NAM")), Integer.valueOf(rs.getString("SoNgayCong")), Float.valueOf(rs.getString("MucluongCB")), Float.valueOf(rs.getString("TongPC")), Float.valueOf(rs.getString("TienBH")),
                        Float.valueOf(rs.getString("SoTienThue")), Float.valueOf(rs.getString("TamUng")), Float.valueOf(rs.getString("LuongTH")));
                A.add(bl);
            }

            tblluong.setModel(tableModel);
            tableModel.fireTableDataChanged();
            tren = tblluong.getRowCount();
            lbldemso.setText(duoi + "/" + tren);
            rs.close();
            DatabaseConnection.CloseConnection(conn);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtmabl = new javax.swing.JTextField();
        txtchuvu = new javax.swing.JTextField();
        txttennv = new javax.swing.JTextField();
        txtthang = new javax.swing.JTextField();
        txtnam = new javax.swing.JTextField();
        txtluongcb = new javax.swing.JTextField();
        txtngaycong = new javax.swing.JTextField();
        cbbmanv = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtphucap = new javax.swing.JTextField();
        txtbaohiem = new javax.swing.JTextField();
        txttienthue = new javax.swing.JTextField();
        txttamung = new javax.swing.JTextField();
        txtluongthuc = new javax.swing.JTextField();
        txtngaynhan = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtthunhapthang = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtluongdongbaohiem = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtnguoiphuthuoc = new javax.swing.JTextField();
        btntinhluong = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        lbl5 = new javax.swing.JLabel();
        lbl6 = new javax.swing.JLabel();
        lbl7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btndau = new javax.swing.JButton();
        btntruoc = new javax.swing.JButton();
        btnsau = new javax.swing.JButton();
        btncuoi = new javax.swing.JButton();
        lbldemso = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblluong = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        btnthem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnIn = new javax.swing.JButton();
        btnluu = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        btnin2 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        btntimkiem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BẢNG TÍNH LƯƠNG NHÂN VIÊN TRONG CÔNG TY");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel1.setText("Mã bảng lương:");

        jLabel2.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel2.setText("Mã nhân viên:");

        jLabel3.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel3.setText("Tên nhân viên:");

        jLabel4.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel4.setText("Chức vụ:");

        jLabel5.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel5.setText("Tháng:");

        jLabel6.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel6.setText("Năm:");

        jLabel7.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel7.setText("Mức lương CB:");

        jLabel8.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel8.setText("Số ngày công:");

        txtmabl.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txtmabl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));

        txtchuvu.setEditable(false);
        txtchuvu.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txtchuvu.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txttennv.setEditable(false);
        txttennv.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txttennv.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txtthang.setEditable(false);
        txtthang.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txtthang.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txtnam.setEditable(false);
        txtnam.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txtnam.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txtluongcb.setEditable(false);
        txtluongcb.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txtluongcb.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        txtluongcb.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtluongcbInputMethodTextChanged(evt);
            }
        });

        txtngaycong.setEditable(false);
        txtngaycong.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txtngaycong.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        cbbmanv.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        cbbmanv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbmanv.setPreferredSize(new java.awt.Dimension(58, 25));
        cbbmanv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbmanvItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtluongcb, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtngaycong, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnam, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtthang, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtchuvu, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttennv, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmabl, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtmabl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txttennv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtchuvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtthang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtnam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtluongcb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtngaycong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));

        jLabel9.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel9.setText("Tổng phụ cấp:");

        jLabel10.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel10.setText("Tiền Bảo hiểm:");

        jLabel11.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel11.setText("Số tiền thuế:");

        jLabel12.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel12.setText("Tạm ứng:");

        jLabel13.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel13.setText("Lương thực nhận:");

        jLabel14.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel14.setText("Ngày nhận lương:");

        txtphucap.setEditable(false);
        txtphucap.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txtphucap.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txtbaohiem.setEditable(false);
        txtbaohiem.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txtbaohiem.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txttienthue.setEditable(false);
        txttienthue.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txttienthue.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txttamung.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txttamung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));

        txtluongthuc.setEditable(false);
        txtluongthuc.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txtluongthuc.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        txtluongthuc.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtluongthucCaretUpdate(evt);
            }
        });

        txtngaynhan.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txtngaynhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtphucap, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(txtbaohiem)
                    .addComponent(txttienthue)
                    .addComponent(txttamung)
                    .addComponent(txtluongthuc)
                    .addComponent(txtngaynhan))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtphucap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtbaohiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txttienthue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txttamung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtluongthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtngaynhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tính thuế và bảo hiểm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Quicksand Light", 1, 14))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel15.setText("Nhập dữ liệu để tính tiền thuế TNCN và tiền bảo hiểm cần đóng: ");

        jLabel16.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel16.setText("Thu nhập tháng (Bao gồm tiền lương và tiền trợ cấp nếu có):");

        txtthunhapthang.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txtthunhapthang.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel17.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel17.setText("Lương đóng bảo hiểm:");

        txtluongdongbaohiem.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txtluongdongbaohiem.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel18.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        jLabel18.setText("Số người phụ thuộc:");

        txtnguoiphuthuoc.setEditable(false);
        txtnguoiphuthuoc.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        txtnguoiphuthuoc.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        btntinhluong.setText("Tính lương");
        btntinhluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntinhluongActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 204));

        lbl1.setFont(new java.awt.Font("Quicksand Light", 1, 12)); // NOI18N
        lbl1.setPreferredSize(new java.awt.Dimension(41, 15));

        lbl2.setFont(new java.awt.Font("Quicksand Light", 1, 12)); // NOI18N
        lbl2.setPreferredSize(new java.awt.Dimension(41, 15));

        lbl3.setFont(new java.awt.Font("Quicksand Light", 1, 12)); // NOI18N
        lbl3.setPreferredSize(new java.awt.Dimension(41, 15));

        lbl4.setFont(new java.awt.Font("Quicksand Light", 1, 12)); // NOI18N
        lbl4.setPreferredSize(new java.awt.Dimension(41, 15));

        lbl5.setFont(new java.awt.Font("Quicksand Light", 1, 12)); // NOI18N
        lbl5.setPreferredSize(new java.awt.Dimension(41, 15));

        lbl6.setFont(new java.awt.Font("Quicksand Light", 1, 12)); // NOI18N
        lbl6.setPreferredSize(new java.awt.Dimension(41, 15));

        lbl7.setFont(new java.awt.Font("Quicksand Light", 1, 12)); // NOI18N
        lbl7.setPreferredSize(new java.awt.Dimension(41, 15));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addContainerGap(240, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btntinhluong, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtluongdongbaohiem, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtnguoiphuthuoc, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtthunhapthang, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtthunhapthang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtluongdongbaohiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnguoiphuthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btntinhluong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 204));

        btndau.setBackground(new java.awt.Color(204, 204, 204));
        btndau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HumanManagerForm/Icon/previous.png"))); // NOI18N
        btndau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndauActionPerformed(evt);
            }
        });

        btntruoc.setBackground(new java.awt.Color(204, 204, 204));
        btntruoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HumanManagerForm/Icon/rewind-button.png"))); // NOI18N
        btntruoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntruocActionPerformed(evt);
            }
        });

        btnsau.setBackground(new java.awt.Color(204, 204, 204));
        btnsau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HumanManagerForm/Icon/forward-button.png"))); // NOI18N
        btnsau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsauActionPerformed(evt);
            }
        });

        btncuoi.setBackground(new java.awt.Color(204, 204, 204));
        btncuoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HumanManagerForm/Icon/next.png"))); // NOI18N
        btncuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncuoiActionPerformed(evt);
            }
        });

        lbldemso.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btndau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btntruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbldemso, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 61, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnsau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btndau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbldemso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btntruoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncuoi, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        tblluong.setFont(new java.awt.Font("Quicksand Light", 1, 12)); // NOI18N
        tblluong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblluong.setRowHeight(25);
        tblluong.setSelectionBackground(new java.awt.Color(255, 204, 0));
        tblluong.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblluong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblluongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblluong);

        jPanel8.setBackground(new java.awt.Color(255, 255, 204));

        btnthem.setFont(new java.awt.Font("Quicksand Light", 1, 13)); // NOI18N
        btnthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HumanManagerForm/Icon/new.png"))); // NOI18N
        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Quicksand Light", 1, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HumanManagerForm/Icon/delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnIn.setFont(new java.awt.Font("Quicksand Light", 1, 13)); // NOI18N
        btnIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HumanManagerForm/Icon/excel.png"))); // NOI18N
        btnIn.setText("XUẤT EXCEL");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });

        btnluu.setFont(new java.awt.Font("Quicksand Light", 1, 13)); // NOI18N
        btnluu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HumanManagerForm/Icon/floppy-disk.png"))); // NOI18N
        btnluu.setText("lưu");
        btnluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnluuActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Quicksand Light", 1, 13)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HumanManagerForm/Icon/log-out.png"))); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        btnin2.setFont(new java.awt.Font("Quicksand Light", 1, 14)); // NOI18N
        btnin2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HumanManagerForm/Icon/printer.png"))); // NOI18N
        btnin2.setText("In báo cáo");
        btnin2.setActionCommand("");
        btnin2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnin2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnin2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnthem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnluu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnluu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnIn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnin2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel19.setFont(new java.awt.Font("Quicksand Medium", 1, 36)); // NOI18N
        jLabel19.setText("BẢNG TÍNH LƯƠNG");

        txttimkiem.setFont(new java.awt.Font("Quicksand Light", 1, 13)); // NOI18N
        txttimkiem.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        txttimkiem.setPreferredSize(new java.awt.Dimension(60, 25));

        btntimkiem.setFont(new java.awt.Font("Quicksand Light", 1, 13)); // NOI18N
        btntimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HumanManagerForm/Icon/search.png"))); // NOI18N
        btntimkiem.setText("Tìm kiếm");
        btntimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(517, 517, 517))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btntimkiem)
                        .addGap(556, 556, 556))
                    .addComponent(jScrollPane1)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbmanvItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbmanvItemStateChanged
        // TODO add your handling code here:
        try {
            String n = cbbmanv.getItemAt(cbbmanv.getSelectedIndex());
            String sql = "SELECT NV.HOTENNV,NV.NGUOIPHUTHUOC,CV.TENCV,CC.THANG,CC.NAM,NV.LUONGCOBAN,CC.SONGAYLAM,PC.TONGCHIPHI FROM NHANVIEN AS NV,BANGCHAMCONG AS CC,PHUCAP AS PC,CHUCVU AS CV"
                    + " WHERE NV.MANV=CC.MANV AND NV.MACV=CV.MACV AND PC.MAPHUCAP = NV.MAPHUCAP AND NV.MANV =?";
            Connection conn = DatabaseConnection.openConnection();
            PreparedStatement pm = conn.prepareStatement(sql);
            pm.setString(1, n);
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                txttennv.setText(rs.getString("HOTENNV"));
                txtchuvu.setText(rs.getString("TENCV"));
                txtthang.setText(rs.getString("THANG"));
                txtnam.setText(rs.getString("NAM"));
                txtluongcb.setText(rs.getString("LUONGCOBAN"));
                txtngaycong.setText(rs.getString("SONGAYLAM"));
                txtphucap.setText(rs.getString("TONGCHIPHI"));
                txtnguoiphuthuoc.setText(rs.getString("NGUOIPHUTHUOC"));
            }
            rs.close();
            pm.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbbmanvItemStateChanged

    private void tblluongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblluongMouseClicked
        // TODO add your handling code here:
        int row = tblluong.getSelectedRow();
        if (row >= 0) {
            duoi = row + 1;
            lbldemso.setText(duoi + " / " + tren);
            cbbmanv.setEnabled(false);
            txtmabl.setText(tblluong.getValueAt(row, 0).toString());
            cbbmanv.setSelectedItem(tblluong.getValueAt(row, 1).toString());
            txttennv.setText(tblluong.getValueAt(row, 2).toString());
            txtchuvu.setText(tblluong.getValueAt(row, 3).toString());
            txtthang.setText(tblluong.getValueAt(row, 4).toString());
            txtnam.setText(tblluong.getValueAt(row, 5).toString());
            txtluongcb.setText(tblluong.getValueAt(row, 6).toString());
            txtngaycong.setText(tblluong.getValueAt(row, 7).toString());
            txtphucap.setText(tblluong.getValueAt(row, 8).toString());
            txtbaohiem.setText(tblluong.getValueAt(row, 9).toString());
            txttienthue.setText(tblluong.getValueAt(row, 10).toString());
            txttamung.setText(tblluong.getValueAt(row, 11).toString());
            txtluongthuc.setText(tblluong.getValueAt(row, 12).toString());
            txtngaynhan.setText(tblluong.getValueAt(row, 13).toString());
        }
    }//GEN-LAST:event_tblluongMouseClicked

    private void btncuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncuoiActionPerformed
        // TODO add your handling code here:
        tblluong.setRowSelectionInterval(tren - 1, tren - 1);
        duoi = tren;
        lbldemso.setText(duoi + " / " + tren);
        SuKien();
    }//GEN-LAST:event_btncuoiActionPerformed

    private void btnsauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsauActionPerformed
        // TODO add your handling code here:
        if (duoi + 1 > tren) {
            return;
        }
        duoi += 1;
        lbldemso.setText(duoi + " / " + tren);
        tblluong.setRowSelectionInterval(duoi - 1, duoi - 1);
        SuKien();
    }//GEN-LAST:event_btnsauActionPerformed

    private void btndauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndauActionPerformed
        // TODO add your handling code here:
        tblluong.setRowSelectionInterval(0, 0);
        duoi = 1;
        lbldemso.setText(duoi + " / " + tren);
        SuKien();

    }//GEN-LAST:event_btndauActionPerformed

    private void btntruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntruocActionPerformed
        if (duoi - 1 < 1) {
            return;
        }
        duoi -= 1;
        lbldemso.setText(duoi + " / " + tren);
        tblluong.setRowSelectionInterval(duoi - 1, duoi - 1);
        SuKien();        // TODO add your handling code here:
    }//GEN-LAST:event_btntruocActionPerformed
    private void tinh() {
        float luongcb = Float.parseFloat(txtluongcb.getText());
        float phucap = Float.parseFloat(txtphucap.getText());
        int songaycong = Integer.parseInt(txtngaycong.getText());
        int nam = Integer.parseInt(txtnam.getText());
        int thang = Integer.parseInt(txtthang.getText());
        int songaylam = 0;
        switch (thang) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                songaylam = 27;
                break;
            case 2: {
                if (nam % 4000 == 0 || (nam % 4 == 0 && nam % 100 != 0)) {
                    songaylam = 25;
                } else {
                    songaylam = 24;
                }
                break;
            }
            case 9:
            case 6:
            case 4:
            case 11:
                songaylam = 26;
                break;
        }
        luongcb = luongcb / songaylam * songaycong;
        txtthunhapthang.setText("" + (luongcb + phucap));
        txtluongdongbaohiem.setText(txtthunhapthang.getText());
    }
    private void txtluongthucCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtluongthucCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtluongthucCaretUpdate

    private void txtluongcbInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtluongcbInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtluongcbInputMethodTextChanged

    private void btntinhluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntinhluongActionPerformed
        // TODO add your handling code here:
        tinh();
        float luongthang = Float.parseFloat(txtthunhapthang.getText());
        float luongbaohiem = Float.parseFloat(txtluongdongbaohiem.getText());
        int nguoiphuthuoc = Integer.parseInt(txtnguoiphuthuoc.getText());
        float sotiendongbaohiem = 0;
        float sotiengiamnguoiphuthuoc = 0;
        lbl1.setText("Mức đóng: BHXH (8%), BHYT (1.5%), BHTN (1%)");
        if (luongbaohiem <= 85172000 && luongbaohiem >= 27800000) {
            lbl2.setText("Mức lương tối đa để đóng BHXH,BHYT là 27,800,000");
            sotiendongbaohiem = (float) (27800000 * 0.08 + 27800000 * 0.015 + luongbaohiem * 0.01);
            lbl3.setText("Bảo hiểm bắt buộc: " + ((27800000 + "* 0.08 +" + 27800000 + "* 0.015 +" + luongbaohiem + "* 0.01 ") + "= " + sotiendongbaohiem));
        } else if (luongbaohiem > 85172000) {
            lbl2.setText("Mức lương tối đa để đóng BHXH,BHYT là 27,800,000 BHTN là 85,172,000");
            sotiendongbaohiem = (float) (27800000 * 0.08 + 27800000 * 0.015 + 85172000 * 0.01);
            lbl3.setText("Bảo hiểm bắt buộc: " + ((27800000 + "* 0.08 +" + 27800000 + "* 0.015 +" + 85172000 + "* 0.01 ") + "= " + sotiendongbaohiem));
        } else {
            sotiendongbaohiem = (float) (luongbaohiem * 0.08 + luongbaohiem * 0.015 + luongbaohiem * 0.01);
            lbl3.setText("Bảo hiểm bắt buộc: " + ((luongbaohiem + "* 0.08 +" + luongbaohiem + "* 0.015 +" + luongbaohiem + "* 0.01 ") + "= " + sotiendongbaohiem));
        }
        lbl4.setText("Giảm trừ bản thân: 11,000,000");
        sotiengiamnguoiphuthuoc = nguoiphuthuoc * 4400000;
        lbl5.setText("Giảm trừ người phụ thuộc: " + nguoiphuthuoc + " * 4,400,000 " + "= " + sotiengiamnguoiphuthuoc);
        float thunhapchiuthue = 0;
        thunhapchiuthue = luongthang - 11000000 - sotiengiamnguoiphuthuoc - sotiendongbaohiem;
        if (thunhapchiuthue < 0) {
            thunhapchiuthue = 0;
        }
        lbl6.setText("Thu nhập chịu thuế: " + luongthang + " - 11000000 - " + sotiengiamnguoiphuthuoc + " - " + sotiendongbaohiem + " = " + thunhapchiuthue);
        float mucthueapdung = 0;
        if (thunhapchiuthue <= 5000000) {
            mucthueapdung = (float) (thunhapchiuthue * 0.05);
            lbl7.setText("Mức thuế áp dụng là: " + thunhapchiuthue + " * 0.05 = " + mucthueapdung);
        } else if (thunhapchiuthue <= 10000000) {
            mucthueapdung = (float) (thunhapchiuthue * 0.1 - 250000);
            lbl7.setText("Mức thuế áp dụng là: " + thunhapchiuthue + " * 0.1 - 250000 = " + mucthueapdung);
        } else if (thunhapchiuthue <= 18000000) {
            mucthueapdung = (float) (thunhapchiuthue * 0.15 - 750000);
            lbl7.setText("Mức thuế áp dụng là: " + thunhapchiuthue + " * 0.15 - 750000 = " + mucthueapdung);
        } else if (thunhapchiuthue <= 32000000) {
            mucthueapdung = (float) (thunhapchiuthue * 0.2 - 1650000);
            lbl7.setText("Mức thuế áp dụng là: " + thunhapchiuthue + " * 0.2 - 1650000 = " + mucthueapdung);
        } else if (thunhapchiuthue <= 52000000) {
            mucthueapdung = (float) (thunhapchiuthue * 0.25 - 3250000);
            lbl7.setText("Mức thuế áp dụng là: " + thunhapchiuthue + " * 0.25 - 3250000 = " + mucthueapdung);
        } else if (thunhapchiuthue <= 80000000) {
            mucthueapdung = (float) (thunhapchiuthue * 0.3 - 5850000);
            lbl7.setText("Mức thuế áp dụng là: " + thunhapchiuthue + " * 0.3 - 5850000 = " + mucthueapdung);
        } else {
            mucthueapdung = (float) (thunhapchiuthue * 0.35 - 9850000);
            lbl7.setText("Mức thuế áp dụng là: " + thunhapchiuthue + " * 0.35 - 9850000 = " + mucthueapdung);
        }
        txtbaohiem.setText("" + sotiendongbaohiem);
        txttienthue.setText("" + mucthueapdung);
        float tamung = Float.parseFloat(txttamung.getText());
        txtluongthuc.setText("" + (luongthang - mucthueapdung - sotiendongbaohiem - tamung));

    }//GEN-LAST:event_btntinhluongActionPerformed

    private void btnluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnluuActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        if (txtmabl.getText().equals("") || txtngaynhan.getText().equals("")) {
            sb.append("không để thông tin trống");
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String n = cbbmanv.getItemAt(cbbmanv.getSelectedIndex());
            BangLuong BL = new BangLuong();
            BL.setMABL(txtmabl.getText());
            BL.setMANV(n);
            BL.setTENNV(txttennv.getText());
            BL.setCHUCVU(txtchuvu.getText());
            BL.setTHANG(Integer.parseInt(txtthang.getText()));
            BL.setNAM(Integer.parseInt(txtnam.getText()));
            BL.setMucluongCB(Float.parseFloat(txtluongcb.getText()));
            BL.setSONGAYCONG(Integer.parseInt(txtngaycong.getText()));
            BL.setTongPC(Float.parseFloat(txtphucap.getText()));
            BL.setTienBH(Float.parseFloat(txtbaohiem.getText()));
            BL.setSoTienThue(Float.parseFloat(txttienthue.getText()));
            BL.setTamUng(Float.parseFloat(txttamung.getText()));
            BL.setLuongTH(Float.parseFloat(txtluongthuc.getText()));
            BL.setNGAYNHAN(txtngaynhan.getText());
            BangLuongDAO dao = new BangLuongDAO();
            dao.insert(BL);
            loadDulieu();
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Thêm không thành công!");
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnluuActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        cbbmanv.setEnabled(true);
        txtmabl.setText("");
        txttennv.setText("");
        txtchuvu.setText("");
        txtthang.setText("");
        txtnam.setText("");
        txtluongcb.setText("");
        txtngaycong.setText("");
        txtphucap.setText("");
        txtbaohiem.setText("");
        txttienthue.setText("");
        txttamung.setText("");
        txtluongthuc.setText("");
        txtngaynhan.setText("");
        txtmabl.requestFocusInWindow();
    }//GEN-LAST:event_btnthemActionPerformed

    private void btntimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        if (txttimkiem.getText().equals("")) {
            sb.append("Không được để trống!\n");
            txttimkiem.requestFocusInWindow();
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            BangLuongDAO nv = new BangLuongDAO();
            BangLuong NV = nv.Search(txttimkiem.getText());
            if (NV != null) {
                txtmabl.setText(NV.getMABL());
                cbbmanv.setSelectedItem(NV.getMANV());
                txttennv.setText(NV.getTENNV());
                txtchuvu.setText(NV.getCHUCVU());
                txtthang.setText("" + NV.getTHANG());
                txtnam.setText("" + NV.getNAM());
                txtluongcb.setText("" + NV.getMucluongCB());
                txtngaycong.setText("" + NV.getSONGAYCONG());
                txtphucap.setText("" + NV.getTongPC());
                txtbaohiem.setText("" + NV.getTienBH());
                txttienthue.setText("" + NV.getSoTienThue());
                txttamung.setText("" + NV.getTamUng());
                txtluongthuc.setText("" + NV.getLuongTH());
                txtngaynhan.setText("" + NV.getNGAYNHAN());

            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());

        }
    }//GEN-LAST:event_btntimkiemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        if (txtmabl.getText().equals("")) {
            sb.append("Mã bảng lương không được để trống!\n");
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int dk = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa bảng lương của nhân viên này?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (dk == JOptionPane.YES_OPTION) {
            try {
                BangLuongDAO nv = new BangLuongDAO();
                if (nv.Delete(txtmabl.getText())) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công.");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại.");
                }
                loadDulieu();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        GIAODIENCHINH_FORM frm = new GIAODIENCHINH_FORM();
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        // TODO add your handling code here:
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Bảng lương nhân viên tại công ty");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("MABL");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("MANV");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("TENNV");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("CHUCVU");
            cell = row.createCell(4, CellType.NUMERIC);
            cell.setCellValue("THANG");
            cell = row.createCell(5, CellType.NUMERIC);
            cell.setCellValue("NAM");
            cell = row.createCell(6, CellType.NUMERIC);
            cell.setCellValue("LUONGCOBAN");
            cell = row.createCell(7, CellType.NUMERIC);
            cell.setCellValue("SONGAYCONG");
            cell = row.createCell(8, CellType.NUMERIC);
            cell.setCellValue("TONGPC");
            cell = row.createCell(9, CellType.NUMERIC);
            cell.setCellValue("TIENBAOHIEM");
            cell = row.createCell(10, CellType.NUMERIC);
            cell.setCellValue("TIENTHUE");
            cell = row.createCell(11, CellType.NUMERIC);
            cell.setCellValue("TAMUNG");
            cell = row.createCell(12, CellType.NUMERIC);
            cell.setCellValue("LUONGTH");
            cell = row.createCell(13, CellType.STRING);
            cell.setCellValue("NGAYNHAN");

            for (int i = 0; i < A.size(); i++) {
                row = sheet.createRow(4 + i);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(A.get(i).getMABL());
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(A.get(i).getMANV());
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(A.get(i).getTENNV());
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(A.get(i).getCHUCVU());
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(A.get(i).getTHANG());
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(A.get(i).getNAM());
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(A.get(i).getMucluongCB());
                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(A.get(i).getSONGAYCONG());
                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(A.get(i).getTongPC());
                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue(A.get(i).getTienBH());
                cell = row.createCell(10, CellType.STRING);
                cell.setCellValue(A.get(i).getSoTienThue());
                cell = row.createCell(11, CellType.STRING);
                cell.setCellValue(A.get(i).getTamUng());
                cell = row.createCell(12, CellType.STRING);
                cell.setCellValue(A.get(i).getLuongTH());
                cell = row.createCell(13, CellType.STRING);
                cell.setCellValue(A.get(i).getNGAYNHAN());
            }
            File f = new File("D://Bảng lương nhân viên tại công ty.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(f);
                wb.write(fis);
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "In thành công!File được lưu trong ổ đĩa D của máy tính!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "In không thành công!");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnInActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        GIAODIENCHINH_FORM frm = new GIAODIENCHINH_FORM();
        frm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void btnin2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnin2ActionPerformed
        // TODO add your handling code here:
        try {
            Hashtable map = new Hashtable();
            map.put("MABL", txtmabl.getText());
            Connection con = DatabaseConnection.openConnection();
            JasperReport rpt = JasperCompileManager.compileReport("\\QUANLYNHANSU_JAVA\\HumanManager\\src\\HumanManagerForm\\Report\\RP_BangLuong.jrxml");
            JasperPrint p = JasperFillManager.fillReport(rpt, map, con);
            JasperViewer.viewReport(p, false);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_btnin2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BANGLUONG_FORM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BANGLUONG_FORM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BANGLUONG_FORM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BANGLUONG_FORM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BANGLUONG_FORM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btncuoi;
    private javax.swing.JButton btndau;
    private javax.swing.JButton btnin2;
    private javax.swing.JButton btnluu;
    private javax.swing.JButton btnsau;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btntimkiem;
    private javax.swing.JButton btntinhluong;
    private javax.swing.JButton btntruoc;
    private javax.swing.JComboBox<String> cbbmanv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbldemso;
    private javax.swing.JTable tblluong;
    private javax.swing.JTextField txtbaohiem;
    private javax.swing.JTextField txtchuvu;
    private javax.swing.JTextField txtluongcb;
    private javax.swing.JTextField txtluongdongbaohiem;
    private javax.swing.JTextField txtluongthuc;
    private javax.swing.JTextField txtmabl;
    private javax.swing.JTextField txtnam;
    private javax.swing.JTextField txtngaycong;
    private javax.swing.JTextField txtngaynhan;
    private javax.swing.JTextField txtnguoiphuthuoc;
    private javax.swing.JTextField txtphucap;
    private javax.swing.JTextField txttamung;
    private javax.swing.JTextField txttennv;
    private javax.swing.JTextField txtthang;
    private javax.swing.JTextField txtthunhapthang;
    private javax.swing.JTextField txttienthue;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
