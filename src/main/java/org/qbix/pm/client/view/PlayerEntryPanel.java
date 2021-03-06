/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.qbix.pm.client.view;

import javax.swing.JLabel;

import org.qbix.pm.client.model.lol.PlayerParticipant;
import org.qbix.pm.client.model.pm.PlayerEntryDTO;
import org.qbix.pm.client.view.interfaces.PlayerEntryPanelView;

/**
 *
 * @author BigBlackBug
 */
//TODO cancel participation
//TODO confirm participation
//TODO start_game
public class PlayerEntryPanel extends javax.swing.JPanel implements PlayerEntryPanelView{

	private Long accountID;
	
    /**
     * Creates new form PlayerEntryPanel
     */
    public PlayerEntryPanel() {
        initComponents();
    }

	@Override
	public void setConfirmedParticipation(boolean confirmedStake) {
		//TODO fix captions
		statusLabel.setText("ready to play!");
		confirmStakeButton.setEnabled(confirmedStake);
		if(confirmedStake){
			confirmStakeButton.setText("cancel");
		}else{
			confirmStakeButton.setText("accept");
		}
	}
	
	@Override
	public void fill(PlayerParticipant playerParticipant,
			PlayerEntryDTO playerEntry) {
		this.accountID = playerEntry.getAccountId();
		this.playmoreNickName.setText(playerEntry.getNick());
		this.summonerNickLabel.setText(playerEntry.getSummonersNick());
		// TODO icon
	}
	
	@Override
	public void playerDisconnected() {
		// TODO Auto-generated method stub
		
	}

    public Long getAccountID() {
		return accountID;
	}

	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profileIcon = new javax.swing.JLabel();
        playmoreNickName = new javax.swing.JLabel();
        summonerNickLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        confirmStakeButton = new javax.swing.JToggleButton();

        profileIcon.setText("profile_icon");

        playmoreNickName.setText("PlaymoreNickName");

        summonerNickLabel.setText("LOL summoner nickname");

        statusLabel.setText("info/error");

        confirmStakeButton.setText("jToggleButton1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profileIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playmoreNickName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(summonerNickLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                    .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(confirmStakeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(playmoreNickName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(summonerNickLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(confirmStakeButton))
                    .addComponent(profileIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton confirmStakeButton;
    private javax.swing.JLabel playmoreNickName;
    private javax.swing.JLabel profileIcon;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel summonerNickLabel;
    // End of variables declaration//GEN-END:variables

}
