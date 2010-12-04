package GalgGUI;

public class GUI extends javax.swing.JFrame {

  


  /* Creates new form GUI */
    public GUI()
     {
        initComponents();
     }

 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        WordArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        GallowsArea = new javax.swing.JTextArea();
        GuessArea = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        GuessedLetterArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        WordArea.setColumns(20);
        WordArea.setEditable(false);
        WordArea.setFont(new java.awt.Font("Verdana", 0, 14));
        WordArea.setRows(5);
        WordArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        WordArea.setFocusable(false);
        WordArea.setRequestFocusEnabled(false);
        WordArea.setVerifyInputWhenFocusTarget(false);
        WordArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WordAreaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                WordAreaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(WordArea);

        GallowsArea.setColumns(20);
        GallowsArea.setEditable(false);
        GallowsArea.setFont(new java.awt.Font("Verdana", 1, 18));
        GallowsArea.setRows(5);
        GallowsArea.setFocusable(false);
        GallowsArea.setRequestFocusEnabled(false);
        GallowsArea.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(GallowsArea);

        GuessArea.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        GuessedLetterArea.setColumns(20);
        GuessedLetterArea.setEditable(false);
        GuessedLetterArea.setFont(new java.awt.Font("Verdana", 0, 14));
        GuessedLetterArea.setRows(5);
        GuessedLetterArea.setAutoscrolls(false);
        GuessedLetterArea.setFocusable(false);
        GuessedLetterArea.setRequestFocusEnabled(false);
        GuessedLetterArea.setVerifyInputWhenFocusTarget(false);
        jScrollPane3.setViewportView(GuessedLetterArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(GuessArea, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
                .addGap(130, 130, 130)
                .addComponent(GuessArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void WordAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WordAreaKeyTyped

    }//GEN-LAST:event_WordAreaKeyTyped

    private void WordAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WordAreaKeyPressed

    }//GEN-LAST:event_WordAreaKeyPressed

    /**
    * @param args the command line arguments
    */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea GallowsArea;
    public javax.swing.JTextField GuessArea;
    public javax.swing.JTextArea GuessedLetterArea;
    public javax.swing.JTextArea WordArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

}
