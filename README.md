# ansible-jenkins
create a vault-file to store sensitive data by running ```ansible-vault create vault.yml``` 
and set your user-information 
```
---
vault_jenkins_admin_user: "your_jenkins_user"
vault_jenkins_admin_pwd: "your_jenkins_password"
vault_ansible_ssh_user: "your_server_user"
vault_ansible_become_pass: "your_server_password"
```
to edit the vault-file in retrospect
run ```ansible-vault edit vault.yml```

run ansible to install jenkins with ```ansible-playbook site.yml --ask-vault-pass```
