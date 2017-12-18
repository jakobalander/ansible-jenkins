# ansible-jenkins
run with ```ansible-playbook site.yml```

create a vaulted file to store sensitive data in by running ```ansible-vault create vault.yml``` and set your user-information 
```vault_jenkins_admin_user: "your_jenkins_user"
   vault_jenkins_admin_pwd: "your_jenkins_password"
   vault_ansible_ssh_user: "your_server_user"
   vault_ansible_become_pass: "your_server_password"
   ```
to edit vault-file, type ```ansible-vault edit vault.yml```