package com.edu.fpt.demoparse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import Adapter.CaiDatAdapter;
import fpoly_model.KhoanTC;

public class CaiDatActivity extends Activity {
    Spinner spnLoaiGD, spnLoaiGD2;
    EditText txtGet;
    ImageView imgTS;
    ListView lst;
    ArrayList<String> strListID;
    //LoaiGDDAO lgdDAO;
    String[] lstLoai1 = {"Thu", "Chi", "Tín Dụng", "Tiết Kiệm"};
    ArrayList<String> lstLoai;
    ArrayAdapter<String> adapter, adapter1;
    ArrayList<KhoanTC> listTC = new ArrayList<KhoanTC>();
    //KhoanTCDao ktcDAO;
    CaiDatAdapter adapterCD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);

        //	lgdDAO = new LoaiGDDAO(this);
        //	ktcDAO = new KhoanTCDao(this);
        lstLoai = new ArrayList();
        lstLoai.add("Tất cả");
        lstLoai.add("Thu");
        lstLoai.add("Chi");
        lstLoai.add("Tín Dụng");
        lstLoai.add("Tiết Kiệm");
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, lstLoai);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLoaiGD = (Spinner) findViewById(R.id.spnCD);
        spnLoaiGD.setAdapter(adapter);
        lst = (ListView) findViewById(R.id.lstShowCD);
        imgTS = (ImageView) findViewById(R.id.imgSC);
        spnLoaiGD.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                final int Post = position + 1;
                //Toast.makeText(CaiDatActivity.this, "Load", Toast.LENGTH_LONG).show();
                loadShow(Post);

                lst.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        lvwDssv_onItemClick(position);
                        loadShow(Post);
                    }
                });
                lst.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return false;
                    }

                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        imgTS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(CaiDatActivity.this);
                dialog.setTitle("Thêm Khoản");
                dialog.setContentView(R.layout.themkhoantc);
                final EditText txtMa = (EditText) dialog.findViewById(R.id.edtMaKhoanTC);
                final EditText txtTen = (EditText) dialog.findViewById(R.id.edtTenKhoanTC);
                final Spinner spnNhom = (Spinner) dialog.findViewById(R.id.spnThemTC);
                Button btnThem = (Button) dialog.findViewById(R.id.btnThemTC);
                Button btnReset = (Button) dialog.findViewById(R.id.btnResetTC);
                adapter1 = new ArrayAdapter(CaiDatActivity.this, android.R.layout.simple_spinner_item, lstLoai1);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnNhom.setAdapter(adapter1);
                btnThem.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   final String txtGetMa = txtMa.getText().toString();
                                                   final String txtGetTen = txtTen.getText().toString();
                                                   final String txtNhom = spnNhom.getSelectedItem().toString();
                                                   if (txtGetMa.trim().equalsIgnoreCase("")) {
                                                       Toast.makeText(CaiDatActivity.this, "Mã Trống", Toast.LENGTH_LONG).show();
                                                   } else if (txtGetTen.trim().equalsIgnoreCase("")) {
                                                       Toast.makeText(CaiDatActivity.this, "Tên Trống", Toast.LENGTH_LONG).show();
                                                   } else {
                                                       try {
                                                           //Toast.makeText(CaiDatActivity.this, "" + txtGetMa, Toast.LENGTH_LONG).show();
                                                           ParseQuery<ParseObject> query = ParseQuery.getQuery("KhoanTC");
                                                           query.whereEqualTo("KhoanGD", txtGetMa);
                                                           query.findInBackground(new FindCallback<ParseObject>() {
                                                               @Override
                                                               public void done(List<ParseObject> objects, com.parse.ParseException e) {
                                                                   if (objects.size() > 0) {
                                                                       Toast.makeText(CaiDatActivity.this, "Dữ liệu đã tồn tại", Toast.LENGTH_LONG).show();
                                                                   } else {
                                                                       addData(txtGetMa, txtGetTen, txtNhom);
                                                                       Toast.makeText(CaiDatActivity.this, "Thành Công", Toast.LENGTH_SHORT).show();
                                                                   }
                                                               }
                                                           });

                                                           //
                                                       } catch (Exception e) {
                                                           Toast.makeText(CaiDatActivity.this, "Thêm Thất Bại", Toast.LENGTH_LONG).show();
                                                       }
                                                   }
                                                   dialog.dismiss();
                                               }
                                           }

                );
                btnReset.setOnClickListener(new View.OnClickListener()

                                            {

                                                @Override
                                                public void onClick(View v) {
                                                    txtMa.setText("");
                                                    txtTen.setText("");
                                                }
                                            }

                );
                dialog.show();
            }
        });
    }

    public void lvwDssv_onItemClick(final int position) {
        final int p = position;
        final Dialog dialog = new Dialog(CaiDatActivity.this);
        dialog.setTitle("Thêm Khoản");
        dialog.setContentView(R.layout.themkhoantc);
        final EditText txtMa = (EditText) dialog.findViewById(R.id.edtMaKhoanTC);
        final EditText txtTen = (EditText) dialog.findViewById(R.id.edtTenKhoanTC);
        final Spinner spnNhom = (Spinner) dialog.findViewById(R.id.spnThemTC);
        Button btnThem = (Button) dialog.findViewById(R.id.btnThemTC);
        final Button btnReset = (Button) dialog.findViewById(R.id.btnResetTC);
        btnReset.setText("Delete");
        adapter1 = new ArrayAdapter(CaiDatActivity.this, android.R.layout.simple_spinner_item, lstLoai1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNhom.setAdapter(adapter1);
        KhoanTC khoanTC = adapterCD.getItem(position);
        txtTen.setText(khoanTC.TenKhoan);
        //String Ten = txtTen.getText().toString();
        txtMa.setText(khoanTC.KhoanGD);
        //String maTC  = txtMa.getText().toString();
        final String txtIDOB = khoanTC.Nhom;
        if(txtIDOB == "Chi"){
            spnNhom.setSelection(1);
        }else if(txtIDOB == "Tín Dụng"){
            spnNhom.setSelection(2);
        }else if(txtIDOB == "Tiết Kiệm"){
            spnNhom.setSelection(3);
        }
        Toast.makeText(CaiDatActivity.this, "" + txtIDOB, Toast.LENGTH_LONG).show();
        for (int i = 0; i < lstLoai1.length; i++) {
            if (lstLoai1[i].equals(khoanTC.Nhom)) {
                spnNhom.setSelection(i);
            }
        }
        btnThem.setText("Changed");
        btnThem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String txtGetMa = txtMa.getText().toString();
                final String txtGetTen = txtTen.getText().toString();
                String txtNhom = spnNhom.getSelectedItem().toString();
                try {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("KhoanTC");
                    query.getInBackground(txtIDOB, new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject object, com.parse.ParseException e) {
                            if (e == null) {
                                object.put("KhoanGD", txtGetMa);
                                object.put("Tenkhoan", txtGetTen);
                                Toast.makeText(CaiDatActivity.this, "Thay Đổi " + txtGetTen, Toast.LENGTH_LONG).show();
                                Toast.makeText(CaiDatActivity.this, "Thay Đổi Thành Công", Toast.LENGTH_LONG).show();
                                object.saveInBackground();
                            } else {
                                Toast.makeText(CaiDatActivity.this, "That Bai", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(CaiDatActivity.this, "Thay Đổi Thất Bại", Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
                loadShow(1);
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String txtGetMa = txtMa.getText().toString();
                final String txtGetTen = txtTen.getText().toString();
                String txtNhom = spnNhom.getSelectedItem().toString();
                ParseQuery<ParseObject> query = ParseQuery.getQuery("GiaoDich");
                query.whereEqualTo("KhoanGD", txtGetMa);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, com.parse.ParseException e) {
                        if (objects.size() > 0) {
                            Toast.makeText(CaiDatActivity.this, "Dữ liệu đã tồn tại bên trường GD không được xóa!", Toast.LENGTH_SHORT).show();

                        } else {
                            //addData(txtGetMa, txtGetTen, txtNhom);
                            ParseQuery<ParseObject> query1 = ParseQuery.getQuery("KhoanTC");
                            query1.whereEqualTo("KhoanGD", txtGetMa);
                            query1.getInBackground(txtIDOB, new GetCallback<ParseObject>() {
                                @Override
                                public void done(ParseObject object, com.parse.ParseException e) {
                                    if (e == null) {
                                        object.deleteInBackground();
                                        Toast.makeText(CaiDatActivity.this, "Dữ liệu đang Delete", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(CaiDatActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });
                            Toast.makeText(CaiDatActivity.this, "Thành Công!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        dialog.show();
        Toast.makeText(CaiDatActivity.this, "Thông tin chi tiết", Toast.LENGTH_LONG).show();
    }

    public void loadShow(int Post) {
        if (Post == 1) {
            listTC.clear();

            //Lấy tất cả dữ liệu của bảng loaiGiaoDich
            ParseQuery<ParseObject> query = ParseQuery.getQuery("KhoanTC");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> listKhoan, com.parse.ParseException e) {
                    if (e == null) {
                        for (ParseObject ktc : listKhoan) {
                            KhoanTC a = new KhoanTC();
                            a.KhoanGD = ktc.getString("KhoanGD");
                            a.TenKhoan = ktc.getString("Tenkhoan");
                            a.Group = ktc.getString("group");
                            a.Nhom = ktc.getObjectId();
                            listTC.add(a);
                        }
                    } else {
                        Log.d("score", "Error: " + e.getMessage());
                    }
                    adapterCD = new CaiDatAdapter(CaiDatActivity.this, R.layout.custom_listview_caidat, listTC);
                    lst.clearFocus();
                    lst.setAdapter(adapterCD);
                }
            });
        } else {
            String txt = spnLoaiGD.getSelectedItem().toString();
            //Toast.makeText(CaiDatActivity.this, ""+txt, Toast.LENGTH_SHORT).show();
            ParseQuery<ParseObject> query = ParseQuery.getQuery("KhoanTC");
            query.whereEqualTo("group", txt);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> listKhoan, com.parse.ParseException e) {
                    if (e == null) {
                        listTC.clear();
                        for (ParseObject ktc : listKhoan) {
                            KhoanTC a = new KhoanTC();
                            a.KhoanGD = ktc.getString("KhoanGD");
                            a.TenKhoan = ktc.getString("Tenkhoan");
                            a.Group = ktc.getString("group");
                            a.Nhom = ktc.getObjectId();
                            listTC.add(a);
                            // Toast.makeText(CaiDatActivity.this, listTC.size()+"",Toast.LENGTH_LONG).show();
                        }
                    } else {

                    }
                    adapterCD = new CaiDatAdapter(CaiDatActivity.this, R.layout.custom_listview_caidat, listTC);
                    lst.clearFocus();
                    lst.setAdapter(adapterCD);
                }
            });
        }
    }

    public void addData(String Ma, String Ten, String Nhom) {
        ParseObject gameScore = new ParseObject("KhoanTC");
        gameScore.put("KhoanGD", Ma);
        gameScore.put("Tenkhoan", Ten);
        gameScore.put("group", Nhom);
        gameScore.saveInBackground();
        loadShow(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //	getMenuInflater().inflate(R.menu.cai_dat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
